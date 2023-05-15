package com.example.randomuser.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer


@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63)
) {

    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}