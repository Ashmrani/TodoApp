package com.example.todoapp.auth.login

interface LoginContract {

    interface View {
        fun success(mobile: String)
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(errorMessage: String)
    }

    interface Presenter{
        fun onViewCreated()
        fun onAttachView(view: View)
        fun onDetachView()
        fun login(mobile: String)
    }
}