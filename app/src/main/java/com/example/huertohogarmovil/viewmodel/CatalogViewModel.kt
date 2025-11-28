package com.example.huertohogarmovil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.huertohogarmovil.data.repository.ProductRepository
import com.example.huertohogarmovil.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CatalogUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

open class CatalogViewModel(
    private val repo: ProductRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _category = MutableStateFlow("Todos")
    val category: StateFlow<String> = _category.asStateFlow()

    private val _uiState = MutableStateFlow(CatalogUiState())
    open val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    init {
        fetchProducts()
    }

    open fun fetchProducts() {
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch(dispatcher) {
            try {
                val products = repo.getProducts()
                _uiState.value = CatalogUiState(
                    products = products,
                    isLoading = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = CatalogUiState(
                    products = emptyList(),
                    isLoading = false,
                    errorMessage = e.message ?: "Error al cargar productos"
                )
            }
        }
    }

    fun setCategory(cat: String) {
        _category.value = cat
    }

    fun productsFor(cat: String): List<Product> {
        val list = uiState.value.products
        return if (cat == "Todos") list else list.filter { it.category == cat }
    }


}

class CatalogViewModelFactory(
    private val repo: ProductRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatalogViewModel(
                repo = repo,
                dispatcher = Dispatchers.IO
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}