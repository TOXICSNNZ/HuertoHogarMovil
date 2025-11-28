package com.example.huertohogarmovil.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.huertohogarmovil.model.Categories
import com.example.huertohogarmovil.model.Product
import com.example.huertohogarmovil.viewmodel.CartViewModel
import com.example.huertohogarmovil.viewmodel.CatalogViewModel

@Composable
fun CatalogScreen(
    catalogVM: CatalogViewModel,
    cartVM: CartViewModel
) {
    val category by catalogVM.category.collectAsState()
    val state by catalogVM.uiState.collectAsState()

    Column(Modifier.fillMaxSize().padding(12.dp)) {

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(Categories.all) { c ->
                val selected = c == category
                val color by animateColorAsState(
                    targetValue = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                    animationSpec = tween(300)
                )
                Button(
                    onClick = { catalogVM.setCategory(c) },
                    colors = ButtonDefaults.buttonColors(containerColor = color)
                ) { Text(c) }
            }
        }

        Spacer(Modifier.height(8.dp))

        if (state.isLoading) {
            Text("Cargando productos...")
        }

        state.errorMessage?.let {
            Text("Error: $it", color = MaterialTheme.colorScheme.error)
        }

        val products = catalogVM.productsFor(category)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(products, key = { it.code }) { p ->
                ProductItem(p) { cartVM.add(p.code, p.name, p.price) }
            }
        }
    }
}

@Composable
private fun ProductItem(p: Product, onAdd: () -> Unit) {
    var showMsg by remember { mutableStateOf(false) }
    Card(Modifier.fillMaxWidth().clickable {}) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(p.name, style = MaterialTheme.typography.titleMedium)
            p.imageResId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = p.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
            }
            Text("${p.category} • Stock: ${p.stock}")
            Text("$${p.price} CLP / ${p.unit}", color = MaterialTheme.colorScheme.tertiary)
            Button(onClick = {
                onAdd()
                showMsg = true
            }, modifier = Modifier.fillMaxWidth()) { Text("Agregar al carrito") }
            AnimatedVisibility(showMsg) { Text("Agregado ✓", color = MaterialTheme.colorScheme.primary) }
        }
    }
}
