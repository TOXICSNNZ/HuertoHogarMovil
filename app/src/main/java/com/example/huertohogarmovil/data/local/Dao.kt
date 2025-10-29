package com.example.huertohogarmovil.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id DESC LIMIT 1")
    fun getCurrentUser(): Flow<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User)
}

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items ORDER BY id DESC")
    fun getCartItems(): Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: CartItem)

    @Query("DELETE FROM cart_items WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clear()
}
