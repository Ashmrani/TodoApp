package com.example.data.todo

import com.example.domain.todo.TodoRepository
import com.example.domain.todo.model.Todo
import io.reactivex.Single

class TodoImpl(val service: TodoApi) : TodoRepository {

    override fun getTodo(): Single<List<Todo>> {
        return service.getTodo()
    }
}