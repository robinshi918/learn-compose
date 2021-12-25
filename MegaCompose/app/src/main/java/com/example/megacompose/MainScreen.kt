package com.example.megacompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.megacompose.ui.BottomBarScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)


    }
}


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.CloudDrive,
        BottomBarScreen.Photo,
        BottomBarScreen.Home,
        BottomBarScreen.Chat,
        BottomBarScreen.Transfer,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = Color.LightGray) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val icon = when (currentDestination?.route) {
        null -> screen.unselectedIcon
        screen.route -> screen.icon
        else -> screen.unselectedIcon
    }
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = screen.title,
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }

        },
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        modifier = Modifier
            .height(30.dp)
            .align(Alignment.CenterVertically),
        alwaysShowLabel = false

    )
}
