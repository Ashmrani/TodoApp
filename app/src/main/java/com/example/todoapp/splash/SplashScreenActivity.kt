package com.example.todoapp.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.auth.login.LoginActivity
import com.example.todoapp.todo.MainActivity
import com.example.todoapp.utils.PreferenceUtil
import com.example.todoapp.utils.Session

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onStart() {
        super.onStart()
        Handler(Looper.getMainLooper()).postDelayed({
            checkUserSignedIn()
        }, 2000)

    }

    private fun checkUserSignedIn() {
        val session = Session(PreferenceUtil(this))

        if (session.token.isEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }

}