package com.srbastian.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.srbastian.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference = database.reference.child("MyUsers")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.fabId.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
        retrieveDataFromDatabase()
    }

    fun retrieveDataFromDatabase() {
        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (eachUser in snapshot.children) {

                    val users = eachUser.getValue(Users::class.java)
                    if (users != null) {
                        println("User ID: ${users.userId}")
                        println("User ID: ${users.userAge}")
                        println("User ID: ${users.userEmail}")
                        println("User ID: ${users.userName}")
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}