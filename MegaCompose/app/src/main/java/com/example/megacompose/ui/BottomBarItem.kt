package com.example.megacompose.ui

import com.example.megacompose.R

sealed class BottomBarItem(
    val route: String,
    val title: String,
    val selectedIconRes: Int,
) {
    object Home : BottomBarItem(
        route = MegaScreen.Home.route,
        title = "Home",
        selectedIconRes = R.drawable.ic_homepage,
    )

    object CloudDrive : BottomBarItem(
        route = MegaScreen.CloudDrive.route,
        title = "Cloud Drive",
        selectedIconRes = R.drawable.ic_files_home,
    )

    object Photo : BottomBarItem(
        route = MegaScreen.Photos.route,
        title = "Photo",
        selectedIconRes = R.drawable.ic_camera_uploads,
    )

    object Chat : BottomBarItem(
        route = MegaScreen.Chat.route,
        title = "Chat",
        selectedIconRes = R.drawable.ic_chat,
    )

    object Transfer : BottomBarItem(
        route = MegaScreen.Transfer.route,
        title = "Transfer",
        selectedIconRes = R.drawable.ic_shared,
    )
}