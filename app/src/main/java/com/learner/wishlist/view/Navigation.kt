package com.learner.wishlist.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.learner.wishlist.viewModel.WishViewModel

@Composable
fun Navigation(
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(Screen.HomeScreen.route){
            HomeView(navController, viewModel)
        }
        composable(Screen.AddWishScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ){
            val id = if(it.arguments != null) it.arguments!!.getLong("id") else 0L
            AddEditDetailView(id = id, viewModel = viewModel, navController = navController)
        }
    }
}