package com.example.api

import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder


object ApiClient {
    private var apiClient: OkHttpClient?=null
    private var converter: GsonConverterFactory?=null

    val client:OkHttpClient
        get() {
            if (apiClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .addInterceptor(AuthenticationInterceptor)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)  /// show all JSON in logCat
                apiClient = httpBuilder.build()

            }
            return apiClient!!
        }

    val gsonConverter: GsonConverterFactory
        get() {
            if(converter == null){
                converter = GsonConverterFactory
                    .create(GsonBuilder()
                        .setLenient()
                        .disableHtmlEscaping()
                        .create())
            }
            return converter!!
        }

}