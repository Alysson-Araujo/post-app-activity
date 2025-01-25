package com.example.postapp.repositories

import com.example.postapp.data.ApiService
import com.example.postapp.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers(): List<User> = apiService.getUsers()
    suspend fun createUser(user: User): User = apiService.createUser(user)
}