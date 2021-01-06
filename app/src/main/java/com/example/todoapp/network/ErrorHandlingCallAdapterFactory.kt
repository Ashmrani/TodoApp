package com.example.todoapp.network

import com.example.domain.base.ApiException
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException

class ErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    companion object {
        fun create() = ErrorHandlingCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val nextCallAdapter =
            RxJava2CallAdapterFactory.create().get(returnType, annotations, retrofit)
        return RxJava2CallAdapterWrapper(retrofit, nextCallAdapter as CallAdapter<out Any, Any>)
    }

    private class RxJava2CallAdapterWrapper<R>(
        private val retrofit: Retrofit,
        private val callAdapter: CallAdapter<R, Any>
    ) : CallAdapter<R, Any> {

        override fun adapt(call: Call<R>): Any {
            val responseObservable = callAdapter.adapt(call)

            if (responseObservable is Single<*>) {
                return responseObservable.onErrorResumeNext(Function {
                    val ex = if (it is ApiException) {
                        it
                    } else {
                        convertToRequestException(it)
                    }
                    Single.error(ex)
                })
            }

            if (responseObservable is Observable<*>) {
                return responseObservable.onErrorResumeNext(Function {
                    Observable.error(convertToRequestException(it))
                })
            }

            if (responseObservable is Completable) {
                return responseObservable.onErrorResumeNext(Function {
                    val ex = if (it is ApiException) {
                        it
                    } else {
                        convertToRequestException(it)
                    }
                    Completable.error(ex)
                })
            }

            return responseObservable
        }

        override fun responseType(): Type {
            return callAdapter.responseType()
        }

        fun convertToRequestException(throwable: Throwable): Throwable {
            return when (throwable) {
                is HttpException -> handleHttpException(throwable)
                is IOException -> handleNetworkException(throwable)
                is SocketException -> RequestException.timeoutError(throwable as IOException)
                else -> RequestException.unexpectedError(throwable)
            }
        }

        private fun handleNetworkException(ioException: IOException): Throwable {
            return when (ioException) {
                is ConnectException -> RequestException.networkError(ioException)
                is SocketTimeoutException,
                is SocketException -> RequestException.timeoutError(ioException)
                else -> RequestException.networkError(ioException)
            }
        }

        private fun handleHttpException(throwable: HttpException): Throwable {
            return try {
                val response = throwable.response()
                when (response?.code()) {
                    401 -> RequestException.authenticationError("", throwable)
                    400, 404, 500, 501, 502, 503, 504 -> RequestException.serviceError(
                        "",
                        response.code().toString(),
                        throwable
                    )
                    else -> RequestException.unexpectedError(throwable)
                }
            } catch (ex: Exception) {
                RequestException.unexpectedError(throwable)
            }
        }
    }
}