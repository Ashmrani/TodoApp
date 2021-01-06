package com.example.domain.auth

import com.example.domain.auth.model.LoginResponse
import com.example.domain.auth.model.UserProfile
import com.example.domain.auth.usecase.LoginUseCase
import com.example.domain.auth.usecase.OtpUseCase
import io.reactivex.Single

interface AuthRepository {
    fun login(requestValue: LoginUseCase.Request): Single<LoginResponse>
    fun verifyNumber(requestValue: OtpUseCase.Request): Single<UserProfile>
}