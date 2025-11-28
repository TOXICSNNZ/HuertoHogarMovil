package com.example.huertohogarmovil.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UserUi(
    val name: String,
    val email: String,
    val password: String,
    val address: String,
    val photoUri: String? = null
)

class UserViewModel : ViewModel() {

    private val _currentUser = MutableStateFlow<UserUi?>(null)
    val currentUser: StateFlow<UserUi?> = _currentUser.asStateFlow()

    fun saveUser(
        name: String,
        email: String,
        pass: String,
        address: String,
        photoUri: String? = null
    ) {
        _currentUser.value = UserUi(
            name = name,
            email = email,
            password = pass,
            address = address,
            photoUri = photoUri
        )
    }


}