package com.example.postapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postapp.viewmodel.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun mainScreen(viewModel: UserViewModel = viewModel()) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("POST APP") }, backgroundColor = Color(0xFF6200EE)) },
        bottomBar = {
            BottomNavigation(backgroundColor = Color(0xFF6200EE)) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Usuários") },
                    label = { Text("Usuários") },
                    selected = true,
                    onClick = {}
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Posts") },
                    label = { Text("Posts") },
                    selected = false,
                    onClick = {}
                )
            }
        }
    ) { padding ->
        PostScreen(viewModel, Modifier.padding(padding))
    }
}