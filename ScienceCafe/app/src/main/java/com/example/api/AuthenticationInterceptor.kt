package com.example.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response



/**
 * Interceptor used to intercept the actual request and
 * to supply your API Key in REST API calls via a custom header.
 */
object AuthenticationInterceptor : Interceptor {


    var jwtToken: String = ""

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", jwtToken)
            .build()

        return chain.proceed(newRequest)
    }
}