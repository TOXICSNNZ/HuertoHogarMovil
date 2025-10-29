package com.example.huertohogarmovil.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.huertohogarmovil.ui.common.BottomNavBar
import com.example.huertohogarmovil.ui.screens.*
import com.example.huertohogarmovil.viewmodel.*

@Composable
fun AppNavigation(
    sharedVM: SharedViewModel,
    usuarioVM: UsuarioViewModel,
    userVM: UserViewModel,
    catalogVM: CatalogViewModel,
    cartVM: CartViewModel
) {
    val current = sharedVM.currentScreen.collectAsState().value
    val showBottom = current in listOf("catalog","cart","profile")

    Scaffold(
        bottomBar = {
            if (showBottom) {
                BottomNavBar(current = current) { screen -> sharedVM.go(screen) }
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when (current) {
                "welcome" -> WelcomeScreen(onComenzar = { sharedVM.go("register") })
                "register" -> RegistroScreen(
                    vm = usuarioVM,
                    onRegistrado = {
                        val e = usuarioVM.estado.value
                        userVM.saveUser(
                            name = e.nombre,
                            email = e.correo,
                            pass = e.clave,
                            address = e.direccion,
                            photoUri = null
                        )
                        sharedVM.go("catalog")
                    }
                )
                "catalog" -> CatalogScreen(catalogVM, cartVM)
                "cart" -> CartScreen(cartVM)
                "profile" -> ProfileScreen(userVM)
            }
        }
    }
}
