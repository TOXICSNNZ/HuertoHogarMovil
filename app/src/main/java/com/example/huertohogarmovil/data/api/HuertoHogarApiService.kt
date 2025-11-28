package com.example.huertohogarmovil.data.api

import com.example.huertohogarmovil.data.api.dto.ProductDto
import retrofit2.http.GET

interface HuertoHogarApiService {
    @GET("api/products")
    suspend fun getProducts(): List<ProductDto>
}