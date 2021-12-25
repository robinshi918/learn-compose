package com.example.megacompose

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.megacompose.ui.BottomBarItem

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarItem.CloudDrive,
        BottomBarItem.Photo,
        BottomBarItem.Home,
        BottomBarItem.Chat,
        BottomBarItem.Transfer,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = colorResource(id = R.color.grey_020)) {
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
    screen: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val tintColor = when (currentDestination?.route) {
        null -> colorResource(id = R.color.grey_016_white_016)
        screen.route -> colorResource(id = R.color.red_600_red_300)
        else -> colorResource(id = R.color.grey_016_white_016)
    }

    BottomNavigationItem(
        icon = {
            Icon(
                painterResource(id = screen.selectedIconRes),
                contentDescription = screen.title,
                tint = tintColor
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
