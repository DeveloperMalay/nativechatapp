package com.example.nativechatapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nativechatapp.data.Event
import com.example.nativechatapp.data.UserData
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LCViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {


    var inProgress = mutableStateOf(false)
    val eventMutableState = mutableStateOf<Event<String>?>(null)
    var isSignedIn = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    fun signUp(name: String, number: String, email: String, password: String) {
        inProgress.value = true
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                inProgress.value = false
                isSignedIn.value = true
                createOrUpdateUser(name, number)
                Log.d("Tag", "signUp: User Logged in")
            } else {
                handleException(it.exception)
                Log.d("Tag", "signUp: User creating unsuccessfull ${it.result}")
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


