package com.example.todoapp.network

import com.example.domain.base.AppExceptions
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

private val onFailureStub: (exception: AppExceptions) -> Unit = {}
private val onAuthenticationErrorStub: (exception: RequestException) -> Unit = {}
private val onSubscribeStub: (d: Disposable) -> Boolean = { false }
private val onProgressStub: (show: Boolean) -> Unit = {}

class CompletableRequestSubscriber(
    private val onSuccess: () -> Unit,
    private val onFailure: (exception: AppExceptions) -> Unit = onFailureStub,
    private val onAuthenticationError: (e: RequestException) -> Unit = onAuthenticationErrorStub,
    private val onShowProgress: (show: Boolean) -> Unit = onProgressStub,
    private val onSubscribed: (d: Disposable) -> Boolean = onSubscribeStub
) : CompletableObserver {

    override fun onComplete() {
        onShowProgress(false)
        onSuccess()
    }

    override fun onSubscribe(d: Disposable) {
        onSubscribed(d)
        onShowProgress(true)
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        onShowProgress(false)
        when (throwable) {
            is RequestException -> handleRequestException(throwable)
            is AppExceptions -> onFailure(throwable)
            else -> onFailure(AppExceptions(throwable.message ?: "", throwable))
        }
    }

    private fun handleRequestException(requestException: RequestException) {
        when (requestException.kind) {
            RequestException.Kind.AUTHENTICATION_ERROR -> onAuthenticationError(requestException)
            else -> onFailure(requestException)
        }
    }
}