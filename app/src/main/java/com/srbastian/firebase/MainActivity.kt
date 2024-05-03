package com.srbastian.firebase

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

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var buttonSend: Button
    lateinit var textView: TextView

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference: DatabaseReference = database.reference.child("Users")
    private val secondReference: DatabaseReference = database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.etName)
        buttonSend = findViewById(R.id.btnSendData)
        textView = findViewById(R.id.tvName)

        //retrieving data from firebase
        secondReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val realName : String = snapshot.child("Users").child("Name").value.toString()
                textView.text = realName
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        // Send data to the firebase
        buttonSend.setOnClickListener {
            val userName: String = editText.text.toString()
            reference.child("userName").setValue(userName)
        }
    }
}