package com.example.megacompose

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.megacompose.home.HomeScreen
import com.example.megacompose.login.LoginScreen
import com.example.megacompose.ui.screen.ChatScreen
import com.example.megacompose.ui.screen.CloudDriveScreen
import com.example.megacompose.ui.screen.PhotoScreen
import com.example.megacompose.ui.screen.TransferScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    NavHost(
        navController = navController,
        startDestination = MegaScreen.Login.route
    ) {
        composable(route = MegaScreen.Home.route) {
            HomeScreen(scaffoldState, scope)
        }

        composable(route = MegaScreen.CloudDrive.route) {
            CloudDriveScreen(mainViewModel)
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
            LoginScreen(navController, mainViewModel)
        }
    }

}