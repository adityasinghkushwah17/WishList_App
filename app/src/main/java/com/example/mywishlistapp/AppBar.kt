package com.example.mywishlistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable


import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp



import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(
    title: String,
    onBackNavClicked: () -> Unit = {}
) {
    val navigationIcon: (@Composable () -> Unit)? = if (!title.contains("WishList")) {
        {
            IconButton(onClick = { onBackNavClicked() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White // Ensure the color is white for visibility
                )
            }
        }
    } else {
        null
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White, // Ensure the color is white for visibility
                modifier = Modifier
                    .heightIn(24.dp)
                    .padding(start = 2.dp, top = 4.dp),
                textAlign = TextAlign.Start
            )
        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.app_bar_color),
        navigationIcon = navigationIcon
    )
}
