package com.example.megacompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.megacompose.R
import com.example.megacompose.ui.theme.MegaComposeTheme
import com.example.megacompose.ui.theme.Typography

@Composable
fun NavigationView() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        NavigationItem(text = "My Account") {}

        NavigationItem(text = "Contacts") {}

        NavigationItem(text = "Notification") {}

        NavigationItem(text = "Transfers") {}

        NavigationItem(text = "Offline") {}

        NavigationItem(text = "Rubbish Bin") {}

        NavigationItem(text = "Settings") {}

        NavigationItem(text = "UPGRADE") {}
    }

}

@Composable
fun NavigationItem(text: String, onClick: () -> Unit) {
    Column(

    ) {
        Box(
            modifier = Modifier
                .clickable { onClick }
                .height(56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text,
                style = Typography.h6,
            )
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = colorResource(id = R.color.grey_alpha_012)
        )
    }
}

@Composable
fun AccountInfoSection(avatarResId: Int, email: String) {
    Column() {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 1.0f)
        ) {
            Box {
                Image(
                    painter = painterResource(id = avatarResId),
                    contentDescription = "avatar"
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_online_light),
                    contentDescription = "online_offline_status",
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }

    }
}

@Preview
@Composable
fun previewAccountInfoSection() {
    MegaComposeTheme() {
        AccountInfoSection(avatarResId = R.drawable.avatar, email = "a@b.c")
    }
}

@Preview
@Composable
fun previewNavigationItem() {
    MegaComposeTheme {
        NavigationItem(text = "Hello") {

        }
    }
}