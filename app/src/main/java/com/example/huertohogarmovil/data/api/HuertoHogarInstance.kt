package com.example.huertohogarmovil.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HuertoHogarInstance {

    private const val BASE_URL = "http://3.237.24.119:9090/"

    val api: HuertoHogarApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HuertoHogarApiService::class.java)
    }


}