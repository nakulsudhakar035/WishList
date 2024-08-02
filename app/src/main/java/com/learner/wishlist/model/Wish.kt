package com.learner.wishlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name="title")
    val title: String = "",
    @ColumnInfo(name = "description")
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
