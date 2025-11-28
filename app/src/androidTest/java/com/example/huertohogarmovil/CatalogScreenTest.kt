package com.example.huertohogarmovil

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.huertohogarmovil.data.repository.ProductRepository
import com.example.huertohogarmovil.model.Product
import com.example.huertohogarmovil.ui.screens.CatalogScreen
import com.example.huertohogarmovil.ui.theme.HuertoHogarMovilTheme
import com.example.huertohogarmovil.viewmodel.CartViewModel
import com.example.huertohogarmovil.viewmodel.CatalogViewModel
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class AndroidTestFakeProductRepository : ProductRepository() {
    override suspend fun getProducts(): List<Product> {
        return listOf(
            Product(
                code = "FR001",
                name = "Manzanas Fuji",
                price = 1200,
                stock = 150,
                unit = "kilo",
                description = "Prueba",
                category = "Frutas Frescas",
                imageResId = null
            )
        )
    }
}

@RunWith(AndroidJUnit4::class)
class CatalogScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun catalogScreen_muestraNombreDeProducto() {
        val fakeRepo = AndroidTestFakeProductRepository()
        val catalogVM = CatalogViewModel(
            repo = fakeRepo,
            dispatcher = Dispatchers.Unconfined
        )
        val cartVM = CartViewModel()

        composeRule.setContent {
            HuertoHogarMovilTheme {
                CatalogScreen(
                    catalogVM = catalogVM,
                    cartVM = cartVM
                )
            }
        }

        composeRule
            .onNodeWithText("Manzanas Fuji")
            .assertIsDisplayed()
    }
}
