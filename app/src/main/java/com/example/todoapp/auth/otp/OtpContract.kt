package com.example.todoapp.auth.otp

interface OtpContract {
    interface View {
        fun success()
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(errorMessage: String)
        fun showMobile(mobile: String)
    }

    interface Presenter{
        fun onViewCreated()
        fun onAttachView(view: View)
        fun onDetachView()
        fun onVerify(otpNumber: String)
    }
}