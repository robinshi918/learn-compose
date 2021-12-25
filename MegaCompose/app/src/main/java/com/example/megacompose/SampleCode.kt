package com.example.megacompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.megacompose.ui.theme.MegaComposeTheme

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
