package com.example.domain.auth.usecase

import com.example.domain.auth.AuthRepository
import com.example.domain.comman.CompletableUseCase
import io.reactivex.Completable

class LogoutUseCase(private val repository: AuthRepository) :
    CompletableUseCase<LogoutUseCase.Request> {

    override fun execute(requestValues: Request): Completable {
        return repository.logout(requestValues)
    }

    class Request : CompletableUseCase.Request

}