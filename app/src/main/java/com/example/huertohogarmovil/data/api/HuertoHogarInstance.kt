package com.example.huertohogarmovil.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HuertoHogarInstance {

    private const val BASE_URL = "http://34.204.170.77:9090/"

    val api: HuertoHogarApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HuertoHogarApiService::class.java)
    }


}