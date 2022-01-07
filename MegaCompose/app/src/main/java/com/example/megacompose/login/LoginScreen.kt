package com.example.megacompose.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.megacompose.R
import com.example.megacompose.common.MegaButton
import com.example.megacompose.MegaScreen
import com.example.megacompose.ui.theme.Typography
import nz.mega.sdk.MegaError.API_OK
import timber.log.Timber

@Composable
fun showToast(text: String) {
    Toast.makeText(LocalContext.current, text, Toast.LENGTH_LONG).show()
}

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {
    val state = viewModel.result.observeAsState(API_NONE)
    Timber.d("state = ${state.value}")
    when (state.value) {
        API_NONE -> LoginView(viewModel = viewModel)
        API_OK -> navController.navigate(MegaScreen.Home.route) {
            popUpTo(MegaScreen.Home.route) {
                inclusive = false
            }
        }
        else -> LoginErrorView()
    }
}

@Composable
fun LoginErrorView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Login Error", fontSize = 40.sp)
    }
}

@Composable
private fun LoginView(viewModel: LoginViewModel) {
    Column(Modifier.padding(16.dp)) {
        Text(text = "LOG INTO MEGA", style = Typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        val email = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
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
        val password = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        MegaButton("LOGIN") {
            // TODO debounce unnecessary button clicks
            viewModel.login(email.value.text, password.value.text)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Forgot your password?",
            style = Typography.h6,
            color = colorResource(id = R.color.teal_300),
            modifier = Modifier.clickable {}
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Text(text = "New to MEGA?", style = Typography.h6)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Create account", style = Typography.h6,
                color = colorResource(id = R.color.teal_300),
                modifier = Modifier.clickable {}
            )
        }
    }
}
