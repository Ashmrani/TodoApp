package com.example.data.todo

import com.example.domain.todo.model.Todo
import io.reactivex.Single
import retrofit2.http.GET

interface TodoApi {

    @GET("v1/todos")
    fun getTodo(): Single<List<Todo>>
}