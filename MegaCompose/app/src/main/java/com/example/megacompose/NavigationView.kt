package com.example.megacompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.megacompose.R
import com.example.megacompose.common.MegaButton
import com.example.megacompose.ui.theme.MegaComposeTheme
import com.example.megacompose.ui.theme.Typography

@Composable
fun NavigationView() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp)
    ) {

        AccountInfoSection(avatarResId = R.drawable.avatar, email = "a@b.c", name = "Robin Shi")

        NavigationItem(text = "My Account") {}

        NavigationItem(text = "Contacts") {}

        NavigationItem(text = "Notification") {}

        NavigationItem(text = "Transfers") {}

        NavigationItem(text = "Offline") {}

        NavigationItem(text = "Rubbish Bin") {}

        NavigationItem(text = "Settings") {}

        MegaButton(text = "UPGRADE") {

        }
    }
}

/*@Composable
private fun UpgradeButton(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttonColor =
        if (isPressed) colorResource(id = R.color.teal_300).copy(alpha = 0.12f) else colorResource(
            id = R.color.teal_300
        )


    Button(
        onClick = onClick,
        modifier = Modifier.padding(top = 14.dp, bottom = 10.dp),
        elevation = ButtonDefaults.elevation(),
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor)
    ) {
        Text(
            text = "UPGRADE",
            color = colorResource(id = R.color.white_dark_grey)
        )
    }
}*/

@Composable
fun NavigationItem(text: String, onClick: () -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .clickable { onClick() }
                .height(56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text,
                style = Typography.h6,
            )
        }
        Separator()
    }
}

@Composable
private fun Separator() {
    Divider(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(),
        color = colorResource(id = R.color.grey_alpha_012)
    )
}

@Composable
fun AccountInfoSection(
    avatarResId: Int,
    name: String = "",
    email: String,
    businessType: String = "Business"
) {
    Column(
        Modifier.padding(top = 16.dp, bottom = 20.dp)
    ) {
        Box(modifier = Modifier.size(48.dp)) {
            Surface(
                modifier = Modifier.size(30.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 1.0f)
            ) {
                Image(
                    painter = painterResource(id = avatarResId),
                    contentDescription = "avatar"
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_online_light),
                contentDescription = "online_offline_status",
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
        Text(
            text = name,
            style = Typography.h6,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
        Text(text = email, style = Typography.body2)
        Text(
            text = businessType,
            color = colorResource(id = R.color.dark_blue_500_200),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 7.dp)
        )
        LinearProgressIndicator(
            progress = 0.3f,
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            backgroundColor = colorResource(id = R.color.grey_012_white_023),
            color = colorResource(id = R.color.teal_300_teal_200)

        )
        Text(text = "56.2 GB of 405 GB used", Modifier.padding(top = 12.dp))
    }
}


@Preview
@Composable
fun previewAccountInfoSection() {
    MegaComposeTheme() {
        AccountInfoSection(
            avatarResId = R.drawable.avatar,
            email = "email@domain.com",
            name = "Robin Shi"
        )
    }
}
