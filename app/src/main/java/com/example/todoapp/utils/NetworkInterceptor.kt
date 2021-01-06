package com.example.todoapp.utils

import okhttp3.Interceptor

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        return chain.proceed(
            chain.request().newBuilder()
                .build()
        )
    }
}