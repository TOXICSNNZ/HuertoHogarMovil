package com.example.huertohogarmovil.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class UsuarioUiState(
    val nombre: String = "",
    val correo: String = "",
    val clave: String = "",
    val direccion: String = "",
    val aceptaTerminos: Boolean = false,
    val errores: UsuarioErrores = UsuarioErrores()
)

data class UsuarioErrores(
    val nombre: String? = null,
    val correo: String? = null,
    val clave: String? = null,
    val direccion: String? = null
)

class UsuarioViewModel : ViewModel() {
    private val _estado = MutableStateFlow(UsuarioUiState())
    val estado: StateFlow<UsuarioUiState> = _estado

    fun onNombreChange(v: String){ _estado.update { it.copy(nombre = v, errores = it.errores.copy(nombre = null)) } }
    fun onCorreoChange(v: String){ _estado.update { it.copy(correo = v, errores = it.errores.copy(correo = null)) } }
    fun onClaveChange(v: String){ _estado.update { it.copy(clave = v, errores = it.errores.copy(clave = null)) } }
    fun onDireccionChange(v: String){ _estado.update { it.copy(direccion = v, errores = it.errores.copy(direccion = null)) } }
    fun onAceptarTerminosChange(v: Boolean){ _estado.update { it.copy(aceptaTerminos = v) } }

    fun validarFormulario(): Boolean {
        val e = UsuarioErrores(
            nombre = if (estado.value.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!estado.value.correo.contains("@")) "Correo inválido, necesita @" else null,
            clave = if (estado.value.clave.length < 6) "La contraseña necesita mínimo 6 caracteres" else null,
            direccion = if (estado.value.direccion.isBlank()) "Campo obligatorio" else null,
        )
        val hayErrores = listOfNotNull(e.nombre, e.correo, e.clave, e.direccion).isNotEmpty()
        _estado.update { it.copy(errores = e) }
        return !hayErrores && estado.value.aceptaTerminos
    }
}
