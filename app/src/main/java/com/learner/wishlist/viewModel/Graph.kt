package com.learner.wishlist.viewModel

import android.content.Context
import androidx.room.Room
import com.learner.wishlist.model.WishDatabase
import com.learner.wishlist.model.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wish.db").build()
    }
}