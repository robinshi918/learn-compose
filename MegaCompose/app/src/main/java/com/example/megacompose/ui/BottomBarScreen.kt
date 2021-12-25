package com.example.megacompose.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object CloudDrive: BottomBarScreen(
        route = "clouddrive",
        title = "Cloud Drive",
        icon = Icons.Default.FilePresent
    )

    object Photo: BottomBarScreen(
        route = "photo",
        title = "Photo",
        icon = Icons.Default.Image
    )

    object Chat: BottomBarScreen(
        route = "chat",
        title = "Chat",
        icon = Icons.Default.Chat
    )

    object Transfer: BottomBarScreen(
        route = "transfer",
        title = "Transfer",
        icon = Icons.Default.Transform
    )

}