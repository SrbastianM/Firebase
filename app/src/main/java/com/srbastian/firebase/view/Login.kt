package com.srbastian.firebase.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.srbastian.firebase.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        loginBinding.btnLogin.setOnClickListener {
            val email = loginBinding.etEmailLogin.text.toString()
            val password = loginBinding.etPasswordLogin.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginWithFirebase(email, password)
            } else {
                Toast.makeText(applicationContext, "Please fill the fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        loginBinding.btnRegister.setOnClickListener {
            val intent = Intent(this@Login, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginWithFirebase(
        userEmail: String,
        userPassword: String,
    ) {
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login is successfully", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        task.exception?.toString(),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}
