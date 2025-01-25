package com.example.postapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postapp.data.ApiService
import com.example.postapp.models.User
import com.example.postapp.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    var name by mutableStateOf("")
    var email by mutableStateOf("")

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _users.value = apiService.getUsers()
            } catch (e: Exception) {
                Log.e("UserViewModel", "Erro ao carregar usuários", e)
            }
        }
    }

    fun createUser() {
        viewModelScope.launch {
            try {
                val newUser = User(name = name, email = email)
                apiService.createUser(newUser)
                loadUsers()
                name = ""
                email = ""
            } catch (e: Exception) {
                Log.e("UserViewModel", "Erro ao criar usuário", e)
            }
        }
    }
}