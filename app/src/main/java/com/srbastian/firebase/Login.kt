package com.srbastian.firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srbastian.firebase.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        loginBinding.btnLogin.setOnClickListener {
        }
        loginBinding.btnRegister.setOnClickListener {
        }
    }
}
