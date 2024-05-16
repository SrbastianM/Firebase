package com.srbastian.firebase.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.srbastian.firebase.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var registerBinding: ActivityRegisterBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(view)

        supportActionBar?.title = "Register"

        registerBinding.btnRegister.setOnClickListener {
            val email = registerBinding.etEmailRegister.text.toString()
            val password = registerBinding.etPasswordRegister.text.toString()
            registerWithFirebase(email, password)
        }
    }

    private fun registerWithFirebase(
        userEmail: String,
        userPassword: String,
    ) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Your account has created", Toast.LENGTH_SHORT)
                    .show()
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
