package com.example.megacompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.megacompose.ui.screen.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val scaffoldScope = rememberCoroutineScope()
    Scaffold(
        bottomBar = {
            if (shouldShowBottomNavBar(navController = navController)) {
                BottomBar(navController = navController)
            }
        },
        drawerContent = { NavigationView() },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        scaffoldState = scaffoldState
    ) {
        BottomNavGraph(
            navController = navController,
            mainViewModel,
            scaffoldState,
            scaffoldScope
        )
    }
}

@Composable
fun shouldShowBottomNavBar(navController: NavHostController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    return !(route != null && route == MegaScreen.Login.route)
}

@Composable
fun HomeTitleBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    Surface(
        elevation = 4.dp,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val textState = remember { mutableStateOf(TextFieldValue()) }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textState.value,
                onValueChange = { textState.value = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Search in MEGA",
                        fontSize = 15.sp,
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "settings button",
                        modifier = Modifier.clickable {
                            println("setting icon clicked")
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                    )
                },
                trailingIcon = {
                    Surface(
                        modifier = Modifier
                            .size(25.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 1.0f),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = "avatar"
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomBarItem.CloudDrive,
        BottomBarItem.Photo,
        BottomBarItem.Home,
        BottomBarItem.Chat,
        BottomBarItem.Transfer,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = colorResource(id = R.color.grey_020)) {
        items.forEach { screen ->
            AddItem(
                item = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    item: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val tintColor = when (currentDestination?.route) {
        null -> colorResource(id = R.color.grey_016_white_016)
        item.route -> colorResource(id = R.color.red_600_red_300)
        else -> colorResource(id = R.color.grey_016_white_016)
    }

    BottomNavigationItem(
        icon = {
            Icon(
                painterResource(id = item.selectedIconRes),
                contentDescription = item.title,
                tint = tintColor
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == item.route
        } == true,
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.findNode(BottomBarItem.Home.route)!!.id)
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
