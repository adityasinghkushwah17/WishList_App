package com.example.mywishlistapp.data

import com.example.mywishlistapp.Wish
import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun Addwish(wish: Wish){
        wishDao.AddWish(wish)
    }

    fun getAllWishes():Flow<List<Wish>> = wishDao.getAllWish()

    fun getwishbyID(id:Long):Flow<Wish> {

     return wishDao.getAllWishbyID(id)}

    suspend fun Updatewish(wish: Wish){
        wishDao.UpdateAwish(wish)

    }
    suspend fun DeleteAwish(wish: Wish){
        wishDao.deleteWish(wish)
    }
}