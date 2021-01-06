package com.example.data.auth

import com.example.domain.auth.model.LoginResponse
import com.example.domain.auth.model.UserProfile
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("v1/auth")
    fun login(
        @Field("mobile") mobile: String
    ): Single<LoginResponse>

    @FormUrlEncoded
    @POST("v1/auth/otp")
    fun verify(
        @Field("mobile") mobile: String,
        @Field("code") otp: String
    ): Single<UserProfile>
}