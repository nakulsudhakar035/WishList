package com.learner.wishlist.view

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learner.wishlist.viewModel.WishViewModel
import com.learner.wishlist.model.Wish

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
){
    val context = LocalContext.current
    Scaffold (
        topBar = {
            AppBarView(
                title = "WishList",
                onBackNavClicked = {
                    Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "FAB clicked", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.AddWishScreen.route+"/0L")
                },
                modifier = Modifier.padding(all = 20.dp),
                backgroundColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add wish")
            }
        }
    ){
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(it)){
                items(wishList.value,
                    key = {wish ->
                        wish.id
                    }){
                    wish ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if(it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                                viewModel.deleteWish(wish)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                                     val color by animateColorAsState(
                                         if(dismissState.dismissDirection == DismissDirection.EndToStart)
                                            Color.Red
                                         else
                                            Color.Transparent
                                         ,
                                         label = ""
                                     )
                            val alignment = Alignment.CenterEnd
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment
                            ){
                                Icon(imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete a wish",
                                    tint = Color.White)
                            }
                        },
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = {FractionalThreshold(1F)},
                        dismissContent = {
                            WishItem(wish = wish) {
                                val id = wish.id
                                navController.navigate(Screen.AddWishScreen.route+"/$id")
                            }
                        },
                    )
                }
            }
    }
}

@Composable
fun WishItem(wish: Wish, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()
        },
        elevation = 10.dp,
        backgroundColor = Color.White
    ){
        Column (modifier = Modifier.padding(16.dp)){
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }

}