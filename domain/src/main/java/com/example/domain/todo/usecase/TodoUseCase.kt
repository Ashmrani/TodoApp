package com.example.domain.todo.usecase

import com.example.domain.comman.SingleUseCase
import com.example.domain.todo.TodoRepository
import com.example.domain.todo.model.Todo
import io.reactivex.Single

class TodoUseCase(private val repository: TodoRepository) :
    SingleUseCase<TodoUseCase.Request, List<Todo>> {
    override fun execute(requestValues: Request): Single<List<Todo>> {
        return repository.getTodo()
    }

    class Request : SingleUseCase.Request
}