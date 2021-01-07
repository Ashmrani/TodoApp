package com.example.todoapp.todo.main

interface MainContract {
    interface View {
        fun navigateToLogin()
        fun showError(errorMessage: String)
    }

    interface Presenter{
        fun onViewCreated()
        fun onAttachView(view: View)
        fun onDetachView()
        fun logout()
        fun addTodo()
    }
}