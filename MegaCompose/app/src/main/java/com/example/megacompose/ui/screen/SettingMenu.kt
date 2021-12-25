package com.example.megacompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.megacompose.R

@Composable
fun SettingMenu() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(text = "My Account")
        Separator()
        Text(text = "Contacts")
        Separator()
        Text(text = "Notification")
        Separator()
        Text(text = "Transfers")
        Separator()
        Text(text = "Offline")
        Separator()
        Text(text = "Rubbish Bin")
        Separator()
        Text(text = "Settings")
        Separator()
        Text(text = "UPGRADE", modifier = Modifier.clickable { })
    }

}

@Composable
fun Separator(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth(),
        color = colorResource(id = R.color.grey_alpha_012)
    )
}