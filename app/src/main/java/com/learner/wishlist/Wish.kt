package com.learner.wishlist

data class Wish(
    val id: Long = 0L,
    val title: String = "",
    val description:  String = ""
)

object DummyWish{
    val wishList = listOf(
        Wish(title = "Watch", description = "smart watch"),
        Wish(title = "Quest 2", description = "VR headset"),
        Wish(title = "Book", description = "science fiction"),
        Wish(title = "Bean bag", description = "Comfy bag"),
    )
}