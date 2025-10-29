package com.example.huertohogarmovil.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun BottomNavBar(
    current: String,
    onSelect: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = current == "catalog",
            onClick = { onSelect("catalog") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Catálogo") },
            label = { Text("Catálogo") }
        )
        NavigationBarItem(
            selected = current == "cart",
            onClick = { onSelect("cart") },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Carrito") }
        )
        NavigationBarItem(
            selected = current == "profile",
            onClick = { onSelect("profile") },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Perfil") },
            label = { Text("Perfil") }
        )
    }
}
