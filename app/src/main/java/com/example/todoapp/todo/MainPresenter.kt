package com.example.todoapp.todo

import com.example.domain.auth.usecase.LogoutUseCase
import com.example.todoapp.app.di.IoThreadScheduler
import com.example.todoapp.app.di.UiThreadScheduler
import com.example.todoapp.network.CompletableRequestSubscriber
import com.example.todoapp.network.ErrorHandler
import com.example.todoapp.utils.Session
import com.example.todoapp.utils.StringProvider
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(
    @UiThreadScheduler private val uiThread: Scheduler,
    @IoThreadScheduler private val ioThread: Scheduler,
    private val stringProvider: StringProvider,
    private val logoutUseCase: LogoutUseCase,
    private val session: Session
) : MainContract.Presenter {

    private val disposable = CompositeDisposable()

    private var view: MainContract.View? = null

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onAttachView(view: MainContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        disposable.clear()
        this.view = null
    }

    override fun logout() {
        logoutUseCase.execute(LogoutUseCase.Request())
            .observeOn(uiThread)
            .subscribeOn(ioThread)
            .subscribe(CompletableRequestSubscriber({
                session.logout()
                view?.navigateToLogin()
                },
                    { exception ->
                        view?.showError(
                            ErrorHandler.getErrorMessage(
                                exception,
                                stringProvider
                            )
                        )
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