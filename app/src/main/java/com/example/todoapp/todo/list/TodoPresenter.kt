package com.example.todoapp.todo.list

import com.example.domain.todo.model.Todo
import com.example.domain.todo.usecase.TodoUseCase
import com.example.todoapp.app.di.IoThreadScheduler
import com.example.todoapp.app.di.UiThreadScheduler
import com.example.todoapp.network.ErrorHandler
import com.example.todoapp.network.SingleRequestSubscriber
import com.example.todoapp.utils.StringProvider
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TodoPresenter @Inject constructor(
    @UiThreadScheduler private val uiThread: Scheduler,
    @IoThreadScheduler private val ioThread: Scheduler,
    private val stringProvider: StringProvider,
    private val todoUseCase: TodoUseCase
) : TodoContract.Presenter {


    private val disposable = CompositeDisposable()

    private var view: TodoContract.View? = null

    override fun onViewCreated() {
        view?.showProgressBar()
        todoUseCase.execute(TodoUseCase.Request())
            .observeOn(uiThread)
            .subscribeOn(ioThread)
            .subscribe(
                SingleRequestSubscriber({
                    view?.showTodo(it)
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


    override fun onAttachView(view: TodoContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        disposable.clear()
        this.view = null
    }

}