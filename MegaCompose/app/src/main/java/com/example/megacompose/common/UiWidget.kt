package com.example.megacompose.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.megacompose.R

@Composable
fun MegaButton(text: String, onClick: () -> Unit) {
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
            text = text,
            color = colorResource(id = R.color.white_dark_grey)
        )
    }
}