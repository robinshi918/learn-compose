package com.example.megacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.megacompose.ui.theme.MegaComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /*private val loginViewModel: LoginViewModel by viewModels()
    private val cloudDriveViewModel: CloudDriveViewModel by viewModels()*/
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MegaComposeTheme {
                MainScreen(navController = navController, mainViewModel)
            }
        }
    }
}





