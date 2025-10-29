package com.example.huertohogarmovil.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, CartItem::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cartDao(): CartDao
}
