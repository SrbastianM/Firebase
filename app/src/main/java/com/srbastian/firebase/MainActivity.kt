package com.srbastian.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.srbastian.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var usersAdapter: UsersAdapter

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference = database.reference.child("MyUsers")
    var userList = ArrayList<Users>()
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

    private fun retrieveDataFromDatabase() {
        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

                for (eachUser in snapshot.children) {

                    val user = eachUser.getValue(Users::class.java)
                    if (user != null) {
                        userList.add(user)
                        println(userList)
                    }
                    usersAdapter = UsersAdapter(this@MainActivity, userList)

                    mainBinding.rvMainActivity.layoutManager = LinearLayoutManager(this@MainActivity)
                    mainBinding.rvMainActivity.adapter = usersAdapter
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}