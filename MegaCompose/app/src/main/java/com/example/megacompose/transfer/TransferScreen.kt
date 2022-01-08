package com.example.megacompose.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.megacompose.ui.theme.MegaComposeTheme

@Composable
fun TransferScreen() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Transfer",
            fontSize = 40.sp
        )
    }
}

@Preview
@Composable
fun previewTransferScreen() {
    MegaComposeTheme {
        TransferScreen()
    }
}