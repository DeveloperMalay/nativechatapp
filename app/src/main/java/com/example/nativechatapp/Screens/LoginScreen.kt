package com.example.nativechatapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativechatapp.CheckSignedIn
import com.example.nativechatapp.CommonProgressBar
import com.example.nativechatapp.DestinationScreen
import com.example.nativechatapp.LCViewModel
import com.example.nativechatapp.R
import com.example.nativechatapp.navigateTo

@Composable
fun LoginScreen(navController: NavController, vm: LCViewModel) {
    CheckSignedIn(vm = vm, navController = navController)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
//                .size(100.dp)
                .padding(horizontal = 30.dp),
//                .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            val emailState = remember {
                mutableStateOf(TextFieldValue())
            }
            val passwordState = remember {
                mutableStateOf(TextFieldValue())
            }
            val focus = LocalFocusManager.current
            Image(
                painter = painterResource(id = R.drawable.speech),
                contentDescription = null,

                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Sign In", fontSize = 30.sp, fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(8.dp)
            )


            OutlinedTextField(
                label = { Text(text = "Email") },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                singleLine = true,
                value = emailState.value, onValueChange = {
                    emailState.value = it
                })
            OutlinedTextField(
                label = { Text(text = "Password") },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                singleLine = true,
                value = passwordState.value, onValueChange = {
                    passwordState.value = it
                })

            Button(
                onClick = {
                    vm.Login(
                        emailState.value.text,
                        passwordState.value.text
                    )
                }, modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "SIGN IN")
            }
            Text(
                text = "Don't have a Account? Go to SignUp >",

                color = Color.Blue,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, DestinationScreen.SignUp.route)
                    }
            )
        }
    }
    if (vm.inProgress.value) {
        CommonProgressBar()
    }

}