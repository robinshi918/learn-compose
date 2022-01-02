package com.example.megacompose.ui

sealed class MegaScreen(val route: String) {
    object Login : MegaScreen("login_screen")
    object Home : MegaScreen("home_screen")
    object CloudDrive : MegaScreen("cloud_drive_screen")
    object Photos : MegaScreen("photos_screen")
    object Chat : MegaScreen("chat_screen")
    object Transfer: MegaScreen("transfer_screen")
}