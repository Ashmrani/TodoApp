package com.example.todoapp.network

import com.example.domain.base.AppExceptions
import retrofit2.HttpException
import java.io.IOException

class RequestException(
    errorMessage: String,
    val code: String?,
    val kind: Kind,
    exception: Throwable
) : AppExceptions(errorMessage, exception) {

    companion object {
        fun serviceError(
            message: String,
            code: String,
            exception: HttpException
        ): RequestException {
            return RequestException(message, code, Kind.SERVICE_ERROR, exception)
        }

        fun authenticationError(message: String, exception: HttpException): RequestException {
            return RequestException(message, null, Kind.AUTHENTICATION_ERROR, exception)
        }

        fun networkError(exception: IOException): RequestException {
            return RequestException("", null, Kind.NO_NETWORK_ERROR, exception)
        }

        fun timeoutError(exception: IOException): RequestException {
            return RequestException("", null, Kind.TIMEOUT_ERROR, exception)
        }

        fun unexpectedError(exception: Throwable): RequestException {
            return RequestException("", null, Kind.UNEXPECTED, exception)
        }
    }

    enum class Kind {
        AUTHENTICATION_ERROR,
        SERVICE_ERROR,
        NO_NETWORK_ERROR,
        TIMEOUT_ERROR,
        UNEXPECTED
    }
}