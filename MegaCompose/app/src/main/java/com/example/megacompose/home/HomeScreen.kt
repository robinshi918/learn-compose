package com.example.megacompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.megacompose.HomeTitleBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeTitleBar()
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

