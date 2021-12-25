package com.example.megacompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.megacompose.ui.BottomBarScreen
import com.example.megacompose.ui.screen.*


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }

        composable(route = BottomBarScreen.CloudDrive.route) {
            CloudDriveScreen()
        }

        composable(route = BottomBarScreen.Photo.route) {
            PhotoScreen()
        }

        composable(route = BottomBarScreen.Chat.route) {
            ChatScreen()
        }

        composable(route = BottomBarScreen.Transfer.route) {
            TransferScreen()
        }
    }

}