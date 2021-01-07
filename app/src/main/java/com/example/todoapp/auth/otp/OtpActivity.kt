package com.example.todoapp.auth.otp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.todo.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_otp.*
import javax.inject.Inject

@AndroidEntryPoint
class OtpActivity : AppCompatActivity(), OtpContract.View {

    @Inject
    lateinit var presenter: OtpPresenter

    companion object {
        private const val TAG = "OtpActivity"
        private const val EXTRA_MOBILE = "EXTRA_MOBILE"

        fun getIntent(mobile: String, context: Context): Intent {
            val intent = Intent(context, OtpActivity::class.java)
            intent.putExtra(EXTRA_MOBILE, mobile)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val mobile = intent.getStringExtra(EXTRA_MOBILE)

        presenter.onAttachView(this)
        phoneNumber.text = mobile

        verifyButtton.setOnClickListener {
            presenter.onVerify(otpTextInputEditText.text.toString(), mobile!!)
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
        startActivity(Intent(this, MainActivity::class.java))
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