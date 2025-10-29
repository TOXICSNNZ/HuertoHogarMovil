package com.example.huertohogarmovil.model

import androidx.annotation.DrawableRes

data class Product(
    val code: String,
    val name: String,
    val price: Int,
    val stock: Int,
    val unit: String,
    val description: String,
    val category: String,
    @DrawableRes val imageResId: Int? = null
)

object Categories {
    val all = listOf("Todos","Frutas Frescas","Verduras Orgánicas","Productos Orgánicos","Productos Lácteos")
}
