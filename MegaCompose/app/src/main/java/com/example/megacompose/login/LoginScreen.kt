package com.example.megacompose.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.megacompose.R
import com.example.megacompose.common.MegaButton
import com.example.megacompose.ui.theme.MegaComposeTheme
import com.example.megacompose.ui.theme.Typography

@Composable
fun LoginScreen() {

    Column(Modifier.padding(16.dp)) {


        Text(text = "LOG INTO MEGA", style = Typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        val emailTextState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = emailTextState.value,
            onValueChange = { emailTextState.value = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.teal_300)
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = "email",
                    fontSize = 15.sp,
                )
            },
            label = {
                Text(text = "email", color = colorResource(id = R.color.teal_300))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))
        val passwordTextState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = passwordTextState.value,
            onValueChange = { passwordTextState.value = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.teal_300)
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = "password",
                    fontSize = 15.sp,
                )
            },
            label = {
                Text(
                    text = "password",
                    color = colorResource(id = R.color.teal_300)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))

        MegaButton("LOGIN") {

        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Forgot your password?",
            style = Typography.h6,
            color = colorResource(id = R.color.teal_300),
            modifier = Modifier.clickable{}
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Text(text = "New to MEGA?", style = Typography.h6)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Create account", style = Typography.h6,
                color = colorResource(id = R.color.teal_300),
                modifier = Modifier.clickable{}

            )
        }
    }
}


@Preview
@Composable
fun previewLoginScreen() {
    MegaComposeTheme {
        LoginScreen()
    }
}