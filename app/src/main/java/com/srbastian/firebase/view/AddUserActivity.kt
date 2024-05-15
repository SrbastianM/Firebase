package com.srbastian.firebase.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.srbastian.firebase.databinding.ActivityAddUserBinding
import com.srbastian.firebase.model.Users

class AddUserActivity : AppCompatActivity() {
    lateinit var addUserBinding: ActivityAddUserBinding

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("MyUsers")

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
        val age: Int = addUserBinding.etAge.text.toString().toInt()
        val email: String = addUserBinding.etEmail.text.toString()

        // unique key provide for firebase
        val id: String = myReference.push().key.toString()

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
    }
}
