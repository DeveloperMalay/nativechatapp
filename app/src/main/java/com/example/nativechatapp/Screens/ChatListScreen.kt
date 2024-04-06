package com.example.nativechatapp.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nativechatapp.CommonProgressBar
import com.example.nativechatapp.LCViewModel

@Composable
fun ChatListScreen(navController: NavController, vm: LCViewModel) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val inProgress = vm.inProgress;
        if (inProgress.value) {
            CommonProgressBar()
        } else {
            Text(
                "Chats", modifier = Modifier
                    .padding(8.dp)
            )
        }

        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.CHATLIST,
            navController = navController
        )
    }
}

fun FAB(showDialog: Boolean) {

}