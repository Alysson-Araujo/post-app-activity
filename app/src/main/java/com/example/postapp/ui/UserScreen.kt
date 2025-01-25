package com.example.postapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.postapp.models.User
import com.example.postapp.viewmodel.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun postScreen (userViewModel: UserViewModel, modifier: Modifier = Modifier) {

    val users by userViewModel.users.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campos para adicionar usuário
        TextField(
            value = userViewModel.name,
            onValueChange = { userViewModel.name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = userViewModel.email,
            onValueChange = { userViewModel.email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { userViewModel.createUser() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Criar Usuário")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de usuários
        LazyColumn {
            items(users) { user -> // `users` agora é tratado como uma lista
                userCard(user = user)
            }
        }
    }
}

@Composable
fun userCard(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Name: ${user.name}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Email: ${user.email}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
