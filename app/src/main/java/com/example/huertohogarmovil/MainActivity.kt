package com.example.huertohogarmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.huertohogarmovil.data.repository.ProductRepository
import com.example.huertohogarmovil.ui.navigation.AppNavigation
import com.example.huertohogarmovil.ui.theme.HuertoHogarMovilTheme
import com.example.huertohogarmovil.viewmodel.CartViewModel
import com.example.huertohogarmovil.viewmodel.CatalogViewModel
import com.example.huertohogarmovil.viewmodel.CatalogViewModelFactory
import com.example.huertohogarmovil.viewmodel.SharedViewModel
import com.example.huertohogarmovil.viewmodel.UserViewModel
import com.example.huertohogarmovil.viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HuertoHogarMovilTheme {

                val sharedVM: SharedViewModel = viewModel()
                val userVM: UserViewModel = viewModel()
                val cartVM: CartViewModel = viewModel()
                val usuarioVM: UsuarioViewModel = viewModel()

                val productRepo = ProductRepository()
                val catalogVM: CatalogViewModel = viewModel(
                    factory = CatalogViewModelFactory(productRepo)
                )

                AppNavigation(
                    sharedVM = sharedVM,
                    catalogVM = catalogVM,
                    cartVM = cartVM,
                    userVM = userVM,
                    usuarioVM = usuarioVM
                )
            }
        }
    }
}
