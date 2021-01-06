package com.example.todoapp.auth.login

interface LoginContract {

    interface View {
        fun success(mobile: String)
    }

    interface Presenter{
        fun onViewCreated()
        fun login(mobile: String)
    }
}