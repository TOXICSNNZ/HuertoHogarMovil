package com.example.huertohogarmovil.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.huertohogarmovil.viewmodel.CartViewModel

@Composable
fun CartScreen(vm: CartViewModel) {
    val state = vm.state.collectAsState().value
    val ctx = LocalContext.current

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Carrito", style = MaterialTheme.typography.headlineSmall)

        LazyColumn(Modifier.weight(1f)) {
            items(state.items, key = { it.id }) { item ->
                Card {
                    Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(Modifier.weight(1f)) {
                            Text(item.name, style = MaterialTheme.typography.titleMedium)
                            Text("${item.quantity} x $${item.unitPrice} CLP")
                        }
                        IconButton(onClick = { vm.remove(item.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }
        }

        Text("Total: $${state.total} CLP", style = MaterialTheme.typography.titleMedium)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { vm.clear() }, modifier = Modifier.weight(1f)) { Text("Vaciar") }
            Button(onClick = {
                if (state.items.isNotEmpty()) {
                    val detalle = buildString {
                        append("Pedido HuertoHogar\n")
                        state.items.forEach { append("- ${it.name} (${it.quantity} x $${it.unitPrice})\n") }
                        append("Total: $${state.total} CLP\n")
                    }
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_SUBJECT, "Boleta HuertoHogar")
                        putExtra(Intent.EXTRA_TEXT, detalle)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    val chooser = Intent.createChooser(intent, "Compartir pedido")
                    try {
                        ctx.startActivity(chooser)
                    } catch (e: Exception) {
                        Toast.makeText(ctx, "No hay apps para compartir", Toast.LENGTH_SHORT).show()
                    }

                }
            }, modifier = Modifier.weight(1f)) { Text("Confirmar pedido") }
        }
    }
}
