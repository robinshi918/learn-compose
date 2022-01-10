package com.example.megacompose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.megacompose.R
import com.example.megacompose.ui.theme.MegaComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeTitleBar(scaffoldState, scope)
        HomeContent()
    }
}

@Composable
private fun HomeContent() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HomeTabs()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTabs() {
    val tabData = listOf(
        "Recent",
        "Offline"
    )
    val pagerState = rememberPagerState(
        pageCount = tabData.size,
        initialOffscreenLimit = 2,
        initialPage = 0
    )

    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color.Transparent,
            contentColor = Color.Red,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            tabData.forEachIndexed { index, label ->
                Tab(selected = index == tabIndex,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }, text = {
                        Text(
                            text = label,
                            color = if (index == tabIndex) Color.Red else Color.Gray
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { index ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (index) {
                    0 -> RecentScreen()
                    1 -> OfflineScreen()
                }
            }
        }
    }


}
@Preview
@Composable
fun PreviewbAR() {
    MegaComposeTheme() {
        HomeTitleBar(scaffoldState =afds , scope = asfasd)
    }
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
