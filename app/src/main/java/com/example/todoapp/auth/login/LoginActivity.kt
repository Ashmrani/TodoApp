package com.example.todoapp.auth.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButtton.setOnClickListener {

        }
    }
}