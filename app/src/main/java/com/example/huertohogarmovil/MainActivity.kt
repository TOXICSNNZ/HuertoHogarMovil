package com.example.huertohogarmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.huertohogarmovil.data.local.AppDatabase
import com.example.huertohogarmovil.data.repository.*
import com.example.huertohogarmovil.ui.navigation.AppNavigation
import com.example.huertohogarmovil.ui.theme.HuertoHogarMovilTheme
import com.example.huertohogarmovil.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "huerto_db"
            ).fallbackToDestructiveMigration(false).build()

        val userRepo = UserRepository(db.userDao())
        val cartRepo = CartRepository(db.cartDao())
        val catalogRepo = CatalogRepository()

        setContent {
            HuertoHogarMovilTheme {
                val sharedVM: SharedViewModel = viewModel()
                val usuarioVM: UsuarioViewModel = viewModel()
                val userVM: UserViewModel = viewModel(factory = UserViewModelFactory(userRepo))
                val catalogVM: CatalogViewModel = viewModel(factory = CatalogViewModelFactory(catalogRepo))
                val cartVM: CartViewModel = viewModel(factory = CartViewModelFactory(cartRepo))

                AppNavigation(
                    sharedVM = sharedVM,
                    usuarioVM = usuarioVM,
                    userVM = userVM,
                    catalogVM = catalogVM,
                    cartVM = cartVM
                )
            }
        }
    }
}
