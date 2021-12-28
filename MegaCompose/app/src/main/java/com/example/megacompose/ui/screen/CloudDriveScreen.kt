package com.example.megacompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.megacompose.R
import com.example.megacompose.ui.theme.MegaComposeTheme
import com.example.megacompose.ui.theme.Typography


@Composable
fun CloudDriveScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        FilesTitleBar()
        FilesContent()

    }

}

@Composable
private fun FilesContent() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Cloud Drive",
            fontSize = 40.sp,
            color = Color.Black
        )
    }
}

@Composable
fun FilesTitleBar() {

    TopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(start = 0.dp),
                text = "CLOUD DRIVE", style = Typography.h6
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(end = 0.dp)
                    .clickable {},
                imageVector = Icons.Default.Menu,
                contentDescription = "Settings button"
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
                    .clickable{},
                painter = painterResource(id = R.drawable.ic_menu_search),
                contentDescription = "Search",
            )
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickable{},
                painter = painterResource(id = R.drawable.ic_dots_vertical_grey),
                contentDescription = "Menu",
            )
        }

    )

    /*Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_homepage),
            contentDescription = "Settings button",
            modifier = Modifier.clickable { })
        Text(text = "CLOUD DRIVE", style = Typography.h6)
        Icon(painter = painterResource(id = R.drawable.ic_chat), contentDescription = "search button")
    }*/
}

@Preview
@Composable
fun previewCloudDrive() {
    MegaComposeTheme {
        CloudDriveScreen()
    }
}