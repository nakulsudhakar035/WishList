package com.learner.wishlist.view

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object AddWishScreen: Screen("add_wish_screen")
}