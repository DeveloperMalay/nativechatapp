package com.example.nativechatapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nativechatapp.data.Event
import com.example.nativechatapp.data.USER_NODE
import com.example.nativechatapp.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LCViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : ViewModel() {


    var inProgress = mutableStateOf(false)
    val eventMutableState = mutableStateOf<Event<String>?>(null)
    var isSignedIn = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)


    init {
        val currentUser = auth.currentUser
        isSignedIn.value = currentUser != null
        currentUser?.uid?.let {
            getUserData(it)
        }
    }

    fun signUp(name: String, number: String, email: String, password: String) {
        inProgress.value = true
        if (name.isEmpty() or number.isEmpty() or email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = "Please Fill All the fields")
            return
        }

        inProgress.value = true
        db.collection(USER_NODE).whereEqualTo("number", number).get().addOnSuccessListener {
            if (it.isEmpty) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        inProgress.value = false
                        isSignedIn.value = true
                        createOrUpdateUser(name, number)
                        Log.d("Tag", "signUp: User Logged in")
                    } else {
                        handleException(it.exception)
                        Log.d("Tag", "signUp: User creating unsuccessful ${it.result}")
                    }
                }

            } else {
                handleException(customMessage = "number already Exits")
            }
        }

    }

    fun Login(email: String, password: String) {
        if (email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = "Please fill the all field")
            return
        } else {
            inProgress.value = true
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    isSignedIn.value = true
                    inProgress.value = false
                    auth.currentUser?.uid?.let {
                        getUserData(it)
                    }
                }
            }
        }
    }

    private fun createOrUpdateUser(
        name: String? = null,
        number: String? = null,
        imageUrl: String? = null
    ) {
        val uid = auth.currentUser?.uid
        var userData = UserData(
            userId = uid,
            name = name ?: userData.value?.name,
            number = number ?: userData.value?.number,
            imageUrl = imageUrl ?: userData.value?.imageUrl,
        )

        uid?.let {
            inProgress.value = true
            db.collection(USER_NODE).document(uid).get().addOnSuccessListener {
                if (it.exists()) {
                    //update User data
                } else {
                    db.collection(USER_NODE).document(uid).set(userData)
                    inProgress.value = false
                    getUserData(uid)
                }
            }.addOnFailureListener {
                handleException(it, "Cannot Retrieve User")
            }
        }
    }

    private fun getUserData(uid: String) {
        inProgress.value = true

        db.collection(USER_NODE)
            .document(uid).addSnapshotListener { value, error ->
                if (error != null) {
                    handleException(error, "Can not Retrieve User")
                }
                if (value != null) {
                    var user = value.toObject<UserData>()
                    userData.value = user
                    inProgress.value = false
                }
            }
    }

    private fun handleException(exception: Exception? = null, customMessage: String = "") {
        Log.e("NativeChatApp", "NativeChatApp exception: ", exception)
        exception?.printStackTrace()
        val errorMessage = exception?.localizedMessage ?: ""
        val message = customMessage.ifEmpty { errorMessage }
        eventMutableState.value = Event(message)
        inProgress.value = false
    }
}


