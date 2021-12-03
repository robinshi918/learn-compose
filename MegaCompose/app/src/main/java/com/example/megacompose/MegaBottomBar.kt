package com.example.megacompose

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.megacompose.ui.theme.MegaComposeTheme


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
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        }
    }
}

////////////////////////////////////////
@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {

    Row(modifier = modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(MaterialTheme.colors.surface)
        .clickable { println() }
        .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)  //?????
        ) {
            // image goes here
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha.provides(ContentAlpha.medium)) {  // ?????
                Text(text = "3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }


}


@Preview
@Composable
fun PhotographCardPreview() {
    MegaComposeTheme {
        PhotographerCard()
    }
}

@Preview
@Composable
fun playWithButton() {
    Button(onClick = { /*TODO*/ }) {
        Column {
            Text(text = "line 1")
            Text("line 2")

        }
    }
}