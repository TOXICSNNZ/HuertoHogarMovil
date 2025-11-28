package com.example.huertohogarmovil.data.repository

import com.example.huertohogarmovil.R
import com.example.huertohogarmovil.data.api.HuertoHogarInstance
import com.example.huertohogarmovil.data.api.dto.ProductDto
import com.example.huertohogarmovil.model.Product

open class ProductRepository {

    open suspend fun getProducts(): List<Product> {
        val remoteList = HuertoHogarInstance.api.getProducts()
        return remoteList.map { it.toProduct() }
    }

    private fun ProductDto.toProduct(): Product {
        val imageResId = when (code) {
            "FR001" -> R.drawable.manzanas
            "FR002" -> R.drawable.naranjas
            "FR003" -> R.drawable.bananas
            "VR001" -> R.drawable.zanahorias
            "VR002" -> R.drawable.espinacas
            "VR003" -> R.drawable.pimientos
            "PO001" -> R.drawable.miel
            "PO003" -> R.drawable.quinua
            "PL001" -> R.drawable.leche
            else -> null
        }

        return Product(
            code = code,
            name = name,
            price = price,
            stock = stock,
            unit = unit,
            description = description,
            category = category,
            imageResId = imageResId
        )
    }


}