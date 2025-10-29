package com.example.huertohogarmovil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.huertohogarmovil.data.local.User
import com.example.huertohogarmovil.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val currentUser = repository.observeUser().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun saveUser(name: String, email: String, pass: String, address: String, photoUri: String? = null) {
        viewModelScope.launch {
            repository.save(User(name = name, email = email, password = pass, address = address, photoUri = photoUri))
        }
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}
