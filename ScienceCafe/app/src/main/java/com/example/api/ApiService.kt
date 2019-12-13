package com.example.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object  ApiService {
    private val BASE_URL = "https://api.sci-cafe.com"
    private var retrofit: Retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ApiClient.gsonConverter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(ApiClient.client)
        .build()
    val apiService:Retrofit
        get() {
            return retrofit!!
        }

}