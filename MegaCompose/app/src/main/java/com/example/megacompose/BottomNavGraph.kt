package com.example.megacompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.megacompose.login.LoginScreen
import com.example.megacompose.login.LoginViewModel
import com.example.megacompose.ui.MegaScreen
import com.example.megacompose.ui.screen.*


@Composable
fun BottomNavGraph(navController: NavHostController, loginViewModel: LoginViewModel) {
    NavHost(
        navController = navController,
        startDestination = MegaScreen.Login.route
    ) {
        composable(route = MegaScreen.Home.route) {
            HomeScreen()
        }

        composable(route = MegaScreen.CloudDrive.route) {
            CloudDriveScreen()
        }

        composable(route = MegaScreen.Photos.route) {
            PhotoScreen()
        }

        composable(route = MegaScreen.Chat.route) {
            ChatScreen()
        }

        composable(route = MegaScreen.Transfer.route) {
            TransferScreen()
        }

        composable(route = MegaScreen.Login.route) {
            LoginScreen(navController, loginViewModel)
        }



    }

}