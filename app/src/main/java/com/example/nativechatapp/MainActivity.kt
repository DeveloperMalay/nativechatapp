package com.example.nativechatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nativechatapp.Screens.ChatListScreen
import com.example.nativechatapp.Screens.LoginScreen
import com.example.nativechatapp.Screens.ProfileScreen
import com.example.nativechatapp.Screens.SignUpScreen
import com.example.nativechatapp.Screens.SingleChatScreen
import com.example.nativechatapp.Screens.SingleStatusScreen
import com.example.nativechatapp.Screens.StatusScreen
import com.example.nativechatapp.ui.theme.NativechatappTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


sealed class DestinationScreen(var route: String) {
    object SignUp : DestinationScreen("signup")
    object Login : DestinationScreen("login")
    object Profile : DestinationScreen("profile")
    object ChatList : DestinationScreen("chatList")
    object SingleChat : DestinationScreen("singleChat/{chatId}") {
        fun createRoute(id: String) = "singleChat/${id}"
    }

    object StatusList : DestinationScreen("statusList")
    object SingleStatus : DestinationScreen("singleStatus/{userId}") {
        fun createRoute(userId: String) = "singleStatus/${userId}"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
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
    fun ChatAppNavigation() {
        val navController = rememberNavController()
        val vm = hiltViewModel<LCViewModel>()
        NavHost(navController = navController, startDestination = DestinationScreen.SignUp.route) {
            composable(DestinationScreen.SignUp.route) { SignUpScreen(navController, vm) }
            composable(DestinationScreen.Login.route) { LoginScreen(navController, vm) }
            composable(DestinationScreen.ChatList.route) { ChatListScreen(navController, vm) }
            composable(DestinationScreen.Profile.route) { ProfileScreen(navController, vm) }
            composable(DestinationScreen.StatusList.route) { StatusScreen(navController, vm) }
            composable(DestinationScreen.SingleChat.route) { SingleChatScreen() }
            composable(DestinationScreen.SingleStatus.route) { SingleStatusScreen() }
        }

    }

}

