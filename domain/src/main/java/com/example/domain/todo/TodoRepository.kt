package com.example.domain.todo

import com.example.domain.todo.model.Todo
import io.reactivex.Single

interface TodoRepository {

    fun getTodo(): Single<List<Todo>>
}