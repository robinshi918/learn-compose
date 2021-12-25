package com.example.megacompose.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home,
        unselectedIcon = Icons.Default.NoAccounts
    )

    object CloudDrive : BottomBarScreen(
        route = "clouddrive",
        title = "Cloud Drive",
        icon = Icons.Default.FilePresent,
        unselectedIcon = Icons.Default.NoAccounts
    )

    object Photo : BottomBarScreen(
        route = "photo",
        title = "Photo",
        icon = Icons.Default.Image,
        unselectedIcon = Icons.Default.NoAccounts
    )

    object Chat : BottomBarScreen(
        route = "chat",
        title = "Chat",
        icon = Icons.Default.Chat,
        unselectedIcon = Icons.Default.NoAccounts
    )

    object Transfer : BottomBarScreen(
        route = "transfer",
        title = "Transfer",
        icon = Icons.Default.Transform,
        unselectedIcon = Icons.Default.NoAccounts
    )

}