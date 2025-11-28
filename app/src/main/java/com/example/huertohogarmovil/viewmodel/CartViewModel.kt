package com.example.huertohogarmovil.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CartItemUi(
    val id: Int,
    val productCode: String,
    val name: String,
    val unitPrice: Int,
    val quantity: Int
)

data class CartUiState(
    val items: List<CartItemUi> = emptyList(),
    val total: Int = 0
)

class CartViewModel : ViewModel() {

    private val _state = MutableStateFlow(CartUiState())
    val state: StateFlow<CartUiState> = _state.asStateFlow()

    private var nextId = 1

    fun add(code: String, name: String, price: Int) {
        val current = _state.value
        val existing = current.items.find { it.productCode == code }

        val newItems = if (existing != null) {
            current.items.map {
                if (it.productCode == code) it.copy(quantity = it.quantity + 1) else it
            }
        } else {
            current.items + CartItemUi(
                id = nextId++,
                productCode = code,
                name = name,
                unitPrice = price,
                quantity = 1
            )
        }

        _state.value = CartUiState(
            items = newItems,
            total = newItems.sumOf { it.unitPrice * it.quantity }
        )
    }

    fun remove(id: Int) {
        val newItems = _state.value.items.filterNot { it.id == id }
        _state.value = CartUiState(
            items = newItems,
            total = newItems.sumOf { it.unitPrice * it.quantity }
        )
    }

    fun clear() {
        _state.value = CartUiState()
    }


}