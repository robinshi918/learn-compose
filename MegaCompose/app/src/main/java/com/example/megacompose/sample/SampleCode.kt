package com.example.megacompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MegaComposeTheme {
        Greeting("Android")
    }
}

@Preview
@Composable
fun LayoutScaffold() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Hello Title")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Hi There!")
            Text(text = "Thanks for going through the Layouts lesson")
        }

    }
}

@Preview
@Composable
fun SimpleList(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(100) {
            ImageListItem(index = it)
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}
