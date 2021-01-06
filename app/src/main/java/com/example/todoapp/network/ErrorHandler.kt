package com.example.todoapp.network

import com.example.todoapp.utils.StringProvider
import android.text.TextUtils
import com.example.domain.base.AppExceptions
import com.example.todoapp.R

object ErrorHandler {
    fun getErrorMessage(throwable: AppExceptions, stringProvider: StringProvider): String {
        val message = if (throwable is RequestException) when (throwable.kind) {
            RequestException.Kind.SERVICE_ERROR, RequestException.Kind.AUTHENTICATION_ERROR -> throwable.message?.takeIf {
                !TextUtils.isEmpty(
                    it
                )
            }
            RequestException.Kind.NO_NETWORK_ERROR -> stringProvider.provide(R.string.error_network_error_message)
            RequestException.Kind.TIMEOUT_ERROR -> stringProvider.provide(R.string.error_time_out)
            else -> stringProvider.provide(R.string.error_network_error_message)
        } else throwable.message

        return message?.takeIf { !TextUtils.isEmpty(it) }
            ?: stringProvider.provide(R.string.error_internal_error)
    }
}