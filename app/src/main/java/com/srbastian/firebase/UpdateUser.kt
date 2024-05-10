package com.srbastian.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.srbastian.firebase.databinding.ActivityUpdateUserBinding

class UpdateUser : AppCompatActivity() {
    private lateinit var updateUserBinding: ActivityUpdateUserBinding

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("MyUsers")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUserBinding = ActivityUpdateUserBinding.inflate(layoutInflater)
        val view = updateUserBinding.root
        setContentView(view)
        supportActionBar?.title = "Update User"

        getAndSetData()
        updateUserBinding.btnUpdateUser.setOnClickListener {
            updateData()
        }
    }

    private fun getAndSetData() {
        val userName = intent.getStringExtra("userName")
        val userAge = intent.getStringExtra("userAge")
        val userEmail = intent.getStringExtra("userEmail")

        updateUserBinding.etUpdateName.setText(userName)
        updateUserBinding.etUpdateAge.setText(userAge)
        updateUserBinding.etUpdateEmail.setText(userEmail)
    }

    private fun updateData() {
        val updatedName = updateUserBinding.etUpdateName.text.toString()
        val updatedAge = updateUserBinding.etUpdateAge.text.toString().toInt()
        val updatedEmail = updateUserBinding.etUpdateEmail.text.toString()
        val userId = intent.getStringExtra("id").toString()

        val userMap = mutableMapOf<String, Any>()
        userMap["userId"] = userId
        userMap["userName"] = updatedName
        userMap["userAge"] = updatedAge
        userMap["userEmail"] = updatedEmail

        myReference.child(userId).updateChildren(userMap).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "User has been updated", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }
    }
}