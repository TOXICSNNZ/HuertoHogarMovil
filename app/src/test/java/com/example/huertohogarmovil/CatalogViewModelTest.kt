package com.example.huertohogarmovil

import com.example.huertohogarmovil.data.repository.ProductRepository
import com.example.huertohogarmovil.model.Product
import com.example.huertohogarmovil.viewmodel.CatalogViewModel
import com.example.huertohogarmovil.viewmodel.CatalogUiState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

class FakeSuccessProductRepository : ProductRepository() {
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

class FakeErrorProductRepository : ProductRepository() {
    override suspend fun getProducts(): List<Product> {
        throw RuntimeException("Error de red")
    }
}

class CatalogViewModelTest : StringSpec({

    "fetchProducts llena uiState.products cuando el repo tiene exito" {
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val vm = CatalogViewModel(
                repo = FakeSuccessProductRepository(),
                dispatcher = dispatcher
            )

            advanceUntilIdle()

            val state: CatalogUiState = vm.uiState.value

            val expected = listOf(
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

            state.products shouldContainExactly expected
        }
    }

    "fetchProducts deja errorMessage cuando el repo lanza excepcion" {
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val vm = CatalogViewModel(
                repo = FakeErrorProductRepository(),
                dispatcher = dispatcher
            )

            advanceUntilIdle()

            val state: CatalogUiState = vm.uiState.value
            state.errorMessage shouldNotBe null
        }
    }
})
