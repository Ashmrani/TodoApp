package com.example.data.auth

import com.example.domain.auth.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET

interface AuthApi {

    @GET("v1/auth")
    fun login(
        @Field("password") mobile: String
    ): Single<LoginResponse>
}