package com.example.huertohogarmovil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.huertohogarmovil.data.repository.CartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CartUiState(
    val items: List<com.example.huertohogarmovil.data.local.CartItem> = emptyList(),
    val total: Int = 0
)

class CartViewModel(private val repo: CartRepository) : ViewModel() {

    val state = repo.observeCart()
        .map { list -> CartUiState(list, list.sumOf { it.unitPrice * it.quantity }) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), CartUiState())

    fun add(code: String, name: String, price: Int) {
        viewModelScope.launch { repo.addOne(code, name, price) }
    }
    fun remove(id: Int) {
        viewModelScope.launch { repo.remove(id) }
    }
    fun clear() {
        viewModelScope.launch { repo.clear() }
    }
}

class CartViewModelFactory(private val repo: CartRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}














































