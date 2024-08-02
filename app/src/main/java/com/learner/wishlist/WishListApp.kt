package com.learner.wishlist

import android.app.Application
import com.learner.wishlist.viewModel.Graph

class WishListApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}