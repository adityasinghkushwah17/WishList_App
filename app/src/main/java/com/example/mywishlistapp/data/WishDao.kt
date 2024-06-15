package com.example.mywishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mywishlistapp.Wish
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun AddWish(wishEntity: Wish) //better practice to call wish as wishEntity

    @Query("Select * from 'wish_table'")
    abstract fun getAllWish(): Flow<List<Wish>>

    @Update
    abstract suspend fun UpdateAwish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)

    @Query("Select * from 'wish_table' where id=:id")
    abstract fun getAllWishbyID(id:Long): Flow<Wish>
}