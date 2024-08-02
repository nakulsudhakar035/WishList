package com.learner.wishlist.model

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao){

    suspend fun addWish(wish: Wish) {
        wishDao.addWish(wish = wish)
    }

    fun getWishes(): Flow<List<Wish>> {
        return wishDao.getAllWishes()
    }

    fun getWishById(Id: Long) : Flow<Wish> {
        return wishDao.getWishByID(id = Id)
    }

    suspend fun updateWish(wish: Wish) {
        wishDao.updateWish(wish = wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDao.deleteWish(wish = wish)
    }
}