package com.example.todoapp.auth.login

import com.example.domain.auth.usecase.LoginUseCase
import com.example.domain.base.InvalidLoginInputException
import com.example.todoapp.R
import com.example.todoapp.network.ErrorHandler
import com.example.todoapp.network.SingleRequestSubscriber
import com.example.todoapp.utils.StringProvider
import io.reactivex.Scheduler
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val contract: LoginContract.View,
    private val observeOnScheduler: Scheduler,
    private val subscribeOnScheduler: Scheduler,
    private val stringProvider: StringProvider,
    private val loginUseCase: LoginUseCase
) : LoginContract.Presenter {

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun login(mobile: String) {
        loginUseCase.execute(LoginUseCase.Request(mobile))
            .observeOn(observeOnScheduler)
            .subscribeOn(subscribeOnScheduler)
            .subscribe(
                SingleRequestSubscriber({
                    contract.success(it.mobile)
                },
                    { exception ->
                        when (exception) {
                            is InvalidLoginInputException -> {
                                for (error in exception.errors) {
                                    when (error) {
                                        LoginUseCase.LoginInputValidator.ErrorType.MOBILE -> error(
                                            R.string.enter_valid_mobile
                                        )
                                    }
                                }
                            }
                            else -> error(
                                ErrorHandler.getErrorMessage(
                                    exception,
                                    stringProvider
                                )
                            )
                        }
                        contract.hideProgressBar()
                    },
                    {

                    },
                    {

                    },
                    {

                    })
            )
    }
}