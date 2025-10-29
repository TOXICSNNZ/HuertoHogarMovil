package com.example.huertohogarmovil.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productCode: String,
    val name: String,
    val unitPrice: Int,
    val quantity: Int
)
