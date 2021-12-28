package com.example.megacompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.megacompose.R
import com.example.megacompose.clouddrive.CloudDriveDataProvider
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

    val files = remember {
        CloudDriveDataProvider.fileList
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(contentPadding = PaddingValues(horizontal = 10.dp, vertical = 16.dp)) {
            items(
                items = files,
                itemContent = {

                    Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Default.Mail,
                            contentDescription = ""
                        )
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            Text(text = it.fileName, style = Typography.h6)
                            Text(text = it.fileSize)
                        }
                    }


                }
            )
        }
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
                    .clickable {},
                painter = painterResource(id = R.drawable.ic_menu_search),
                contentDescription = "Search",
            )
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickable {},
                painter = painterResource(id = R.drawable.ic_dots_vertical_grey),
                contentDescription = "Menu",
            )
        }

    )
}

@Preview
@Composable
fun previewCloudDrive() {
    MegaComposeTheme {
        CloudDriveScreen()
    }
}