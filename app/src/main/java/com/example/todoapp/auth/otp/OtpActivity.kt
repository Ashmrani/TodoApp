package com.example.todoapp.auth.otp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_otp.*
import javax.inject.Inject

@AndroidEntryPoint
class OtpActivity : AppCompatActivity(), OtpContract.View {

    @Inject
    lateinit var presenter: OtpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onAttachView(this)

        verifyButtton.setOnClickListener {
            presenter.onVerify(otpTextInputEditText.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun showMobile(mobile: String) {
        TODO("Not yet implemented")
    }

    override fun success() {
        startActivity(Intent(this, OtpActivity::class.java))
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        verifyButtton.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
        verifyButtton.visibility = View.VISIBLE
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}