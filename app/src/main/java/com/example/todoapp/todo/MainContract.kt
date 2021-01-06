package com.example.todoapp.todo

interface MainContract {
    interface View {

        fun navigateToLogin()
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(errorMessage: String)
    }

    interface Presenter{
        fun onViewCreated()
        fun onAttachView(view: View)
        fun onDetachView()
        fun logout()
    }
}