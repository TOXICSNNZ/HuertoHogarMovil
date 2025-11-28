package com.example.huertohogarmovil

import com.example.huertohogarmovil.data.api.HuertoHogarApiService
import com.example.huertohogarmovil.data.api.dto.ProductDto
import com.example.huertohogarmovil.data.repository.ProductRepository
import com.example.huertohogarmovil.model.Product
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

class TestTableProductRepository(
    private val testApi: HuertoHogarApiService
) : ProductRepository() {

    override suspend fun getProducts(): List<Product> {
        val remote = testApi.getProducts()
        return remote.map {
            Product(
                code = it.code,
                name = it.name,
                price = it.price,
                stock = it.stock,
                unit = it.unit,
                description = it.description,
                category = it.category,
                imageResId = null
            )
        }
    }
}

class ProductRepositoryTest : StringSpec({

    "getProducts() debe retornar una lista de productos simulada" {

        val fakeRemote = listOf(
            ProductDto(
                code = "FR001",
                name = "Manzanas Fuji",
                price = 1200,
                stock = 150,
                unit = "kilo",
                description = "Prueba",
                category = "Frutas Frescas"
            ),
            ProductDto(
                code = "VR001",
                name = "Zanahorias Orgánicas",
                price = 900,
                stock = 100,
                unit = "kilo",
                description = "Prueba",
                category = "Verduras Orgánicas"
            )
        )

        val mockApi = mockk<HuertoHogarApiService>()
        coEvery { mockApi.getProducts() } returns fakeRemote

        val repo = TestTableProductRepository(mockApi)

        runTest {
            val result = repo.getProducts()

            val expected = fakeRemote.map {
                Product(
                    code = it.code,
                    name = it.name,
                    price = it.price,
                    stock = it.stock,
                    unit = it.unit,
                    description = it.description,
                    category = it.category,
                    imageResId = null
                )
            }

            result shouldContainExactly expected
        }
    }
})
