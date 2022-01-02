package com.example.megacompose.login

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.megacompose.MainScreen


sealed class Screen(val route: String) {
    object Login : Screen("LoginScreen")
    object MainScreen : Screen("MainScreen")
}

@Composable
fun LoginNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(route = Screen.Login.route) {
//            val loginViewModel: LoginViewModel by viewModel<LoginViewModel>()
            LoginScreen(navController)
        }

        navigation(startDestination = "", route = "mainflow") {
            composable("main") {

            }
        }


        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }

    }
}