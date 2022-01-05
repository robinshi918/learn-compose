package com.example.megacompose.login

import android.os.Handler
import android.os.Looper
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.megacompose.R
import com.example.megacompose.common.MegaButton
import com.example.megacompose.login.domain.entity.MegaApiResponseStage
import com.example.megacompose.ui.MegaScreen
import com.example.megacompose.ui.theme.Typography
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nz.mega.sdk.MegaError
import timber.log.Timber

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {


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

            GlobalScope.launch {
                viewModel.login(email.value.text, password.value.text).collect { resp ->
                    Timber.d("receive log responses - ${resp.stage}")
                    if (resp.stage == MegaApiResponseStage.FINISH) {
                        if (resp.error!!.errorCode == MegaError.API_OK) {
                            // TODO: figure out how to run navigation in main thread
                            Handler(Looper.getMainLooper()).post(){
                                navController.navigate(MegaScreen.Home.route)
                            }

                        } else {
                            // TODO login error or show MFA

                        }
                    }
                }
            }
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
