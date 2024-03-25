package com.example.nativechatapp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LCViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {
    fun signUp(name: String, number: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("Tag", "signUp: User Logged in")
            } else {
                Log.d("Tag", "signUp: User creating unsuccessfull ${it.result}")
            }
        }
    }
}