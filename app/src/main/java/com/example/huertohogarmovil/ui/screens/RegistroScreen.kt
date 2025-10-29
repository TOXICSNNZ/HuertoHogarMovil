package com.example.huertohogarmovil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.huertohogarmovil.viewmodel.UsuarioViewModel

@Composable
fun RegistroScreen(
    vm: UsuarioViewModel,
    onRegistrado: () -> Unit
) {
    val estado by vm.estado.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = estado.nombre, onValueChange = vm::onNombreChange,
            label = { Text("Nombre") },
            isError = estado.errores.nombre != null,
            supportingText = { estado.errores.nombre?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = estado.correo, onValueChange = vm::onCorreoChange,
            label = { Text("Correo electrónico") },
            isError = estado.errores.correo != null,
            supportingText = { estado.errores.correo?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = estado.clave, onValueChange = vm::onClaveChange,
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = estado.errores.clave != null,
            supportingText = { estado.errores.clave?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = estado.direccion, onValueChange = vm::onDireccionChange,
            label = { Text("Dirección de entrega") },
            isError = estado.errores.direccion != null,
            supportingText = { estado.errores.direccion?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Checkbox(checked = estado.aceptaTerminos, onCheckedChange = vm::onAceptarTerminosChange)
            Spacer(Modifier.width(8.dp)); Text("Acepto los términos y condiciones")
        }
        Button(
            onClick = { if (vm.validarFormulario()) onRegistrado() },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Registrar / Ingresar") }
    }
}
