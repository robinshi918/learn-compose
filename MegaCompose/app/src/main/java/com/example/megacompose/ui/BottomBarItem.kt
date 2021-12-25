package com.example.megacompose.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.megacompose.R

sealed class BottomBarItem(
    val route: String,
    val title: String,
    val selectedIconRes: Int,
) {
    object Home : BottomBarItem(
        route = "home",
        title = "Home",
        selectedIconRes = R.drawable.ic_homepage,
    )

    object CloudDrive : BottomBarItem(
        route = "clouddrive",
        title = "Cloud Drive",
        selectedIconRes = R.drawable.ic_files_home,
    )

    object Photo : BottomBarItem(
        route = "photo",
        title = "Photo",
        selectedIconRes = R.drawable.ic_camera_uploads,
    )

    object Chat : BottomBarItem(
        route = "chat",
        title = "Chat",
        selectedIconRes = R.drawable.ic_chat,
    )

    object Transfer : BottomBarItem(
        route = "transfer",
        title = "Transfer",
        selectedIconRes = R.drawable.ic_shared,
    )

}