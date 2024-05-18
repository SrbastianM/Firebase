package com.srbastian.firebase.view

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.srbastian.firebase.databinding.ActivityAddUserBinding
import com.srbastian.firebase.model.Users

class AddUserActivity : AppCompatActivity() {
    lateinit var addUserBinding: ActivityAddUserBinding

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = database.reference.child("MyUsers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addUserBinding = ActivityAddUserBinding.inflate(layoutInflater)
        supportActionBar?.title = "Add User"
        val view = addUserBinding.root
        setContentView(view)

        addUserBinding.btnAddUser.setOnClickListener {
            addUserToDatabase()
        }
    }

    private fun addUserToDatabase() {
        val name: String = addUserBinding.etName.text.toString()
        val ageString = addUserBinding.etAge.text.toString()
        val email: String = addUserBinding.etEmail.text.toString()
        // unique key provide for firebase
        val id: String = myReference.push().key.toString()
        val emailPatter = Patterns.EMAIL_ADDRESS

        if (emailPatter.matcher(email).matches() && name.isNotEmpty() && ageString.isNotEmpty()) {
            val age: Int = ageString.toInt()
            val user = Users(id, name, age, email)
            myReference.child(id).setValue(user).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "The user has been added to the database",
                        Toast.LENGTH_SHORT,
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        task.exception.toString(),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
            Toast.makeText(applicationContext, "User has been added", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Please Complete the Fields", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
