package com.example.data.auth

import com.example.domain.auth.AuthRepository
import com.example.domain.auth.model.LoginResponse
import com.example.domain.auth.usecase.LoginUseCase
import io.reactivex.Single

class AuthImpl(private val service: AuthApi) : AuthRepository {

    override fun login(requestValue: LoginUseCase.Request): Single<LoginResponse> {
        return service.login(requestValue.mobile)
    }

}