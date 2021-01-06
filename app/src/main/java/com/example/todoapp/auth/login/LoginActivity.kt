package com.example.todoapp.auth.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.auth.otp.OtpActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.onAttachView(this)

        loginButtton.setOnClickListener {
            presenter.login(mobileNumberTextInputEditText.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun success(mobile: String) {
        startActivity(OtpActivity.getIntent(mobile, this))
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        loginButtton.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
        loginButtton.visibility = View.VISIBLE
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}