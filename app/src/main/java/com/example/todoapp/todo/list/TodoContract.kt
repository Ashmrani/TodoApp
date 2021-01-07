package com.example.todoapp.todo.list

import com.example.domain.todo.model.Todo

interface TodoContract {

    interface View {
        fun showTodo(todo: List<Todo>)
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(errorMessage: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun onAttachView(view: View)
        fun onDetachView()
    }
}