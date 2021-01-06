package com.example.domain.auth.usecase

import com.example.domain.auth.AuthRepository
import com.example.domain.comman.SingleUseCase
import com.example.domain.auth.model.LoginResponse
import com.example.domain.base.InvalidLoginInputException
import io.reactivex.Single

class LoginUseCase(private val repository: AuthRepository) :
    SingleUseCase<LoginUseCase.Request, LoginResponse> {

    override fun execute(requestValues: Request): Single<LoginResponse> {
        val errors = LoginInputValidator().isValid(
            requestValues.mobile,
        )
        return if (errors.isNotEmpty()) {
            Single.create { e -> e.onError(InvalidLoginInputException(errors)) }
        } else {
            repository.login(requestValues)
        }
    }

    class Request(
        val mobile: String
    ) : SingleUseCase.Request

    class LoginInputValidator {

        enum class ErrorType {
            MOBILE,
        }

        fun isValid(
            mobile: String
        ): List<ErrorType> {
            val errors = mutableListOf<ErrorType>()

            if (mobile.isEmpty()) {
                errors.add(ErrorType.MOBILE)
            }

            return errors
        }
    }
}