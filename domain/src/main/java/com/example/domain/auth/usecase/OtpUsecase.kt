package com.example.domain.auth.usecase

import com.example.domain.auth.AuthRepository
import com.example.domain.auth.model.UserProfile
import com.example.domain.base.InvalidOtpInputException
import com.example.domain.comman.SingleUseCase
import com.google.gson.annotations.SerializedName
import io.reactivex.Single

class OtpUseCase(private val repository: AuthRepository) :
    SingleUseCase<OtpUseCase.Request, UserProfile> {

    override fun execute(requestValues: Request): Single<UserProfile> {
        val errors = OtpInputValidator().isValid(requestValues.otp)
        return if (errors.isNotEmpty()) {
            Single.create { e -> e.onError(InvalidOtpInputException(errors)) }
        } else {
            repository.verifyNumber(requestValues)
        }
    }

    class Request(
        @SerializedName("mobile")
        val mobile: String,
        @SerializedName("code")
        val otp: String
    ) : SingleUseCase.Request

    class OtpInputValidator {

        enum class ErrorType {
            OTP,
        }

        fun isValid(
            otpNumber: String
        ): List<ErrorType> {
            val errors = mutableListOf<ErrorType>()

            if (otpNumber.isEmpty()) {
                errors.add(ErrorType.OTP)
            }

            return errors
        }
    }
}