package com.example.todoapp.todo.list.di

import com.example.data.todo.TodoApi
import com.example.data.todo.TodoImpl
import com.example.domain.todo.TodoRepository
import com.example.domain.todo.usecase.TodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import retrofit2.Retrofit


@Module
@InstallIn(FragmentComponent::class)
object TodoModule {

    @FragmentScoped
    @Provides
    fun provideTodoApi(retrofit: Retrofit): TodoApi {
        return retrofit.create(TodoApi::class.java)
    }

    @FragmentScoped
    @Provides
    fun provideTodoRepository(service: TodoApi): TodoRepository =
        TodoImpl(service)

    @FragmentScoped
    @Provides
    fun provideTodoUseCase(todoRepository: TodoRepository) = TodoUseCase(todoRepository)
}