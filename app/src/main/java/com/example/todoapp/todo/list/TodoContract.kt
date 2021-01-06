package com.example.todoapp.todo.list

interface TodoContract {

    interface View {
        fun addTodo()
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(errorMessage: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun onAttachView(view: View)
        fun onDetachView()
        fun onAddTodo()
    }
}