package com.example.domain.auth

import com.example.domain.auth.model.LoginResponse
import com.example.domain.auth.usecase.LoginUseCase
import io.reactivex.Single

interface AuthRepository {
    fun login(requestValue: LoginUseCase.Request): Single<LoginResponse>
}