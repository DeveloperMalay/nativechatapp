package com.example.nativechatapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativechatapp.DestinationScreen
import com.example.nativechatapp.LCViewModel
import com.example.nativechatapp.R

@Composable
fun SignUpScreen(navController: NavController, vm: LCViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            val nameState = remember {
                mutableStateOf(TextFieldValue())
            }
            val numberState = remember {
                mutableStateOf(TextFieldValue())
            }
            val emailState = remember {
                mutableStateOf(TextFieldValue())
            }
            val passwordState = remember {
                mutableStateOf(TextFieldValue())
            }
            Image(
                painter = painterResource(id = R.drawable.speech),
                contentDescription = null,

                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Sign Up", fontSize = 30.sp, fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(8.dp)
            )

            OutlinedTextField(
                label = { Text(text = "Name") },
                modifier = Modifier.padding(8.dp),
                value = nameState.value, onValueChange = {
                    nameState.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Number") },
                modifier = Modifier.padding(8.dp),
                value = numberState.value, onValueChange = {
                    numberState.value = it
                })
            OutlinedTextField(
                label = { Text(text = "Email") },
                modifier = Modifier.padding(8.dp),
                value = emailState.value, onValueChange = {
                    emailState.value = it
                })
            OutlinedTextField(
                label = { Text(text = "Password") },
                modifier = Modifier.padding(8.dp),
                value = passwordState.value, onValueChange = {
                    passwordState.value = it
                })
        }
    }

}