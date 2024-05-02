package com.srbastian.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var buttonSend : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.etName)
        buttonSend = findViewById(R.id.btnSendData)

        buttonSend.setOnClickListener {

            val userName : String = editText.text.toString()


        }
    }
}