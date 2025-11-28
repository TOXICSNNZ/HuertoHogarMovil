package com.example.huertohogarmovil.data.api.dto

data class ProductDto(
    val code: String,
    val name: String,
    val price: Int,
    val stock: Int,
    val unit: String,
    val description: String,
    val category: String
)