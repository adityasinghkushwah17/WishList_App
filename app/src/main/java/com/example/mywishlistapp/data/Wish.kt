package com.example.mywishlistapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish_table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    @ColumnInfo(name = "Wish-Title")
    val title:String="",
    @ColumnInfo(name = "Wish-desc")
    val description:String="")


object DummyData{
    val wishlist= listOf(
        Wish(title = "Google", description = "job at google"),
        Wish(title = "Messii", description = "job at FC BARCA"),
        Wish(title = "IPHONE", description = "Buy I phone"),
    )
}