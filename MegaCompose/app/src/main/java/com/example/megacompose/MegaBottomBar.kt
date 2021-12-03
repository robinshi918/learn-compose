package com.example.megacompose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun MegaBottomBar(selected: Int, onSelectedChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .background(color = Color.Gray)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabItem(
            if (selected == 0) R.drawable.ic_homepage else R.drawable.ic_bottom_navigation_ongoing_group_call_green,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable {
                    onSelectedChange(0)
                }
        )
        TabItem(
            if (selected == 1) R.drawable.ic_homepage else R.drawable.ic_bottom_navigation_ongoing_group_call_green,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable {
                    onSelectedChange(1)
                }
        )
        TabItem(
            if (selected == 2) R.drawable.ic_homepage else R.drawable.ic_bottom_navigation_ongoing_group_call_green,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable {
                    onSelectedChange(2)
                }
        )
        TabItem(
            if (selected == 3) R.drawable.ic_homepage else R.drawable.ic_bottom_navigation_ongoing_group_call_green,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable {
                    onSelectedChange(3)
                }

        )
    }
}


@Composable
fun TabItem(@DrawableRes iconId: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(painter = painterResource(iconId), contentDescription = "")
    }
}