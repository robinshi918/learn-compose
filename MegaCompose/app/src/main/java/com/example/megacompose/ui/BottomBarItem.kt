package com.example.megacompose.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.megacompose.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIconRes: Int,
    val unselectedIcon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home,
        selectedIconRes = R.drawable.ic_homepage,
        unselectedIcon = Icons.Default.NoAccounts
    )

    object CloudDrive : BottomBarScreen(
        route = "clouddrive",
        title = "Cloud Drive",
        icon = Icons.Default.FilePresent,
        selectedIconRes = R.drawable.ic_files_home,
        unselectedIcon = Icons.Default.NoAccounts
    )

    object Photo : BottomBarScreen(
        route = "photo",
        title = "Photo",
        icon = Icons.Default.Image,
        selectedIconRes = R.drawable.ic_camera_uploads,
        unselectedIcon = Icons.Default.NoAccounts
    )

    object Chat : BottomBarScreen(
        route = "chat",
        title = "Chat",
        icon = Icons.Default.Chat,
        selectedIconRes = R.drawable.ic_chat,
        unselectedIcon = Icons.Default.NoAccounts
    )

    object Transfer : BottomBarScreen(
        route = "transfer",
        title = "Transfer",
        icon = Icons.Default.Transform,
        selectedIconRes = R.drawable.ic_shared,
        unselectedIcon = Icons.Default.NoAccounts
    )

}