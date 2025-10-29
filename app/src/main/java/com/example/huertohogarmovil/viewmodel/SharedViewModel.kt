package com.example.huertohogarmovil.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(val items: List<String> = listOf("1","2","3"))

class SharedViewModel : ViewModel() {
    private val _currentScreen = MutableStateFlow("welcome")
    val currentScreen: StateFlow<String> = _currentScreen.asStateFlow()

    private val _selectedCategory = MutableStateFlow("Todos")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    fun go(screen: String) { _currentScreen.value = screen }
    fun setCategory(cat: String) { _selectedCategory.value = cat }
}
