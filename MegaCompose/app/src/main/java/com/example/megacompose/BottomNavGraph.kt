package com.example.megacompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.megacompose.ui.BottomBarItem
import com.example.megacompose.ui.screen.*


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItem.Home.route
    ) {
        composable(route = BottomBarItem.Home.route) {
            HomeScreen()
        }

        composable(route = BottomBarItem.CloudDrive.route) {
            CloudDriveScreen()
        }

        composable(route = BottomBarItem.Photo.route) {
            PhotoScreen()
        }

        composable(route = BottomBarItem.Chat.route) {
            ChatScreen()
        }

        composable(route = BottomBarItem.Transfer.route) {
            TransferScreen()
        }
    }

}