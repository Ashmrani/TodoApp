package com.example.todoapp.network

import android.content.Context
import com.example.data.comman.ApiConstant
import com.example.todoapp.utils.PreferenceUtil
import com.example.todoapp.utils.Session
import okhttp3.Interceptor

class NetworkInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val session = Session(PreferenceUtil(context))
        return if (session.token.isEmpty()){
            chain.proceed(
                chain.request().newBuilder()
                    .header(ApiConstant.LANGUAGE_HEADER, session.userLanguage)
                    .build())
        } else {
            chain.proceed(
                chain.request().newBuilder()
                    .header(ApiConstant.LANGUAGE_HEADER, session.userLanguage)
                    .header(ApiConstant.TOKEN_HEADER, "Bearer ${session.token}")
                    .build())
        }
    }
}