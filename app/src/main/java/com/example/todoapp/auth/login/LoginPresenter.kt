package com.example.todoapp.auth.login

import com.example.domain.auth.usecase.LoginUseCase
import com.example.domain.base.InvalidLoginInputException
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

class LoginPresenter @Inject constructor(
    @UiThreadScheduler private val uiThread: Scheduler,
    @IoThreadScheduler private val ioThread: Scheduler,
    private val stringProvider: StringProvider,
    private val loginUseCase: LoginUseCase,
    private val session: Session
) : LoginContract.Presenter {

    private val disposable = CompositeDisposable()

    private var view: LoginContract.View? = null

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onAttachView(view: LoginContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        disposable.clear()
        this.view = null
    }

    override fun login(mobile: String) {
        loginUseCase.execute(LoginUseCase.Request(mobile))
            .observeOn(uiThread)
            .subscribeOn(ioThread)
            .subscribe(
                SingleRequestSubscriber({
                    view?.success(it.mobile)
                },
                    { exception ->
                        when (exception) {
                            is InvalidLoginInputException -> {
                                for (error in exception.errors) {
                                    when (error) {
                                        LoginUseCase.LoginInputValidator.ErrorType.MOBILE -> view?.showError(
                                            stringProvider.provide(R.string.enter_valid_mobile)
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