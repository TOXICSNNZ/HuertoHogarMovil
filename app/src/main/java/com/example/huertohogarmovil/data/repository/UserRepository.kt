package com.example.huertohogarmovil.data.repository

import com.example.huertohogarmovil.data.local.User
import com.example.huertohogarmovil.data.local.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(private val dao: UserDao) {
    fun observeUser(): Flow<User?> = dao.getCurrentUser()
    suspend fun save(user: User) = dao.saveUser(user)
}
