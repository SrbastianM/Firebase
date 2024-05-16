package com.srbastian.firebase.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.srbastian.firebase.databinding.ActivityForgetBinding

class ForgetActivity : AppCompatActivity() {
    lateinit var forgetBinding: ActivityForgetBinding

    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgetBinding = ActivityForgetBinding.inflate(layoutInflater)
        val view = forgetBinding.root
        setContentView(view)
        supportActionBar?.title = "Forgot Password"

        forgetBinding.btnReset.setOnClickListener {
            val email = forgetBinding.etForgotEmail.text.toString()
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "We sent a password reset email to your mail address",
                        Toast.LENGTH_LONG,
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        task.exception?.toString(),
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        }
    }
}
