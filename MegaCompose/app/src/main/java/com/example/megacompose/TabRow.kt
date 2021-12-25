package com.example.megacompose

import android.media.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable


@Composable
fun <T> TabRow(
    items: List<T>,
    selectedIndex: Int,
    scrollable: Boolean = false,
    tab: @Composable() (Int, T) -> Unit
)  {

}


//@Composable
//fun Tab(text: String? = null, icon: Image? = null, selected: Boolean, onSelected: () -> Unit) {
//    val tint = MaterialTheme.colors.onPrimary
//    when {
//        text != null && icon != null -> CombinedTab(text, icon, selected, onSelected, tint)
//        text != null -> TextTab(text, selected, onSelected, tint)
//        icon != null -> IconTab(icon, selected, onSelected, tint)
//        // Nothing provided here (?!), so let's just draw an empty tab that handles clicks
//        else -> BaseTab(selected, onSelected, {})
//    }
//}