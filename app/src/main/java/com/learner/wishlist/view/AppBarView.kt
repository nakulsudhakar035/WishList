package com.learner.wishlist.view

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.learner.wishlist.R

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
){

    val navigationIcon : (@Composable () -> Unit)? =
        if(!title.contains("WishList")){
            {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Back button"
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
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(24.dp)
            )
        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.bar_color),
        navigationIcon = navigationIcon
    )
}