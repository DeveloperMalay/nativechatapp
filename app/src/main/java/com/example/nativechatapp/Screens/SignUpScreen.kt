package com.example.nativechatapp.Screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.nativechatapp.DestinationScreen
import com.example.nativechatapp.LCViewModel

@Composable
fun SignUpScreen(navController: NavController, vm: LCViewModel) {
    Text(text = "Login", modifier = Modifier.clickable {
        navController.navigate(DestinationScreen.Login.route)
    })

}