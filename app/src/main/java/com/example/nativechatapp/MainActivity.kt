package com.example.nativechatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nativechatapp.Screens.LoginScreen
import com.example.nativechatapp.ui.theme.NativechatappTheme


sealed class DestinationScreen(var route: String){
    object SignUp:DestinationScreen("signup")
    object Login:DestinationScreen("login")
    object Profile:DestinationScreen("profile")
    object ChatList:DestinationScreen("chatList")
    object SingleChat:DestinationScreen("singleChat/{chatId}"){
        fun createRoute(id:String)="singleChat/${id}"
    }
    object StatusList:DestinationScreen("statusList")
    object SingleStatus:DestinationScreen("singleStatus/{userId}"){
        fun createRoute(userId:String)="singleStatus/${userId}"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NativechatappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatAppNavigation()
                }
            }
        }
    }

    @Composable
    fun ChatAppNavigation(){
        LoginScreen()
    }

}

