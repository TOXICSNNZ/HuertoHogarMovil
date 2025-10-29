package com.example.huertohogarmovil.data.repository

import com.example.huertohogarmovil.data.local.CartItem
import com.example.huertohogarmovil.data.local.CartDao
import kotlinx.coroutines.flow.Flow

class CartRepository(private val dao: CartDao) {
    fun observeCart(): Flow<List<CartItem>> = dao.getCartItems()
    suspend fun addOne(code: String, name: String, price: Int) {
        dao.upsert(CartItem(productCode = code, name = name, unitPrice = price, quantity = 1))
    }
    suspend fun remove(id: Int) = dao.deleteById(id)
    suspend fun clear() = dao.clear()
}
