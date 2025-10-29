package com.example.huertohogarmovil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.huertohogarmovil.data.repository.CatalogRepository
import com.example.huertohogarmovil.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CatalogViewModel(private val repo: CatalogRepository) : ViewModel() {
    private val _category = MutableStateFlow("Todos")
    val category: StateFlow<String> = _category.asStateFlow()

    fun setCategory(cat: String){ _category.value = cat }
    fun productsFor(cat: String): List<Product> = if (cat == "Todos") repo.products else repo.products.filter { it.category == cat }
}

class CatalogViewModelFactory(private val repo: CatalogRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatalogViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
