package com.example.domain.base

import com.example.domain.auth.usecase.LoginUseCase
import com.example.domain.auth.usecase.OtpUseCase

open class AppExceptions(errorMsg: String, exception: Throwable? = null) :
    Exception(errorMsg, exception)

class ApiException(errorMsg: String, exception: Throwable? = null) :
    AppExceptions(errorMsg, exception)

class InvalidLoginInputException(val errors: List<LoginUseCase.LoginInputValidator.ErrorType>) :
    AppExceptions("")

class InvalidOtpInputException(val errors: List<OtpUseCase.OtpInputValidator.ErrorType>) :
    AppExceptions("")