package com.example.todoapp.auth.otp

import com.example.domain.auth.usecase.OtpUseCase
import com.example.domain.base.InvalidOtpInputException
import com.example.todoapp.R
import com.example.todoapp.app.di.IoThreadScheduler
import com.example.todoapp.app.di.UiThreadScheduler
import com.example.todoapp.network.ErrorHandler
import com.example.todoapp.network.SingleRequestSubscriber
import com.example.todoapp.utils.Session
import com.example.todoapp.utils.StringProvider
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class OtpPresenter @Inject constructor(
    @UiThreadScheduler private val uiThread: Scheduler,
    @IoThreadScheduler private val ioThread: Scheduler,
    private val stringProvider: StringProvider,
    private val otpUseCase: OtpUseCase,
    private val session: Session
) : OtpContract.Presenter {

    private val disposable = CompositeDisposable()

    private var view: OtpContract.View? = null

    override fun onViewCreated() {
        view?.showMobile(session.userDetails.user?.mobile!!)
    }

    override fun onAttachView(view: OtpContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        disposable.clear()
        this.view = null
    }

    override fun onVerify(otpNumber: String) {
        otpUseCase.execute(OtpUseCase.Request(otpNumber, session.userDetails.user?.mobile!!))
            .observeOn(uiThread)
            .subscribeOn(ioThread)
            .subscribe(
                SingleRequestSubscriber({
                    session.token = it.accessToken!!
                    session.userDetails = it
                    view?.success()
                },
                    { exception ->
                        when (exception) {
                            is InvalidOtpInputException -> {
                                for (error in exception.errors) {
                                    when (error) {
                                        OtpUseCase.OtpInputValidator.ErrorType.OTP -> view?.showError(
                                            stringProvider.provide(R.string.enter_valid_code)
                                        )
                                    }
                                }
                            }
                            else -> view?.showError(
                                ErrorHandler.getErrorMessage(
                                    exception,
                                    stringProvider
                                )
                            )
                        }
                        view?.hideProgressBar()
                    },
                    {
                        view?.showError(
                            ErrorHandler.getErrorMessage(
                                it,
                                stringProvider
                            )
                        )
                    },
                    { showProgressBar ->
                        if (showProgressBar) {
                            view?.showProgressBar()
                        } else {
                            view?.hideProgressBar()
                        }
                    },
                    {
                        disposable.add(it)
                    })
            )
    }
}