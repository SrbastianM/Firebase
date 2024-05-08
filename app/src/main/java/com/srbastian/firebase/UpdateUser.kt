package com.srbastian.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.srbastian.firebase.databinding.ActivityUpdateUserBinding

class UpdateUser : AppCompatActivity() {
    lateinit var updateUserBinding: ActivityUpdateUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUserBinding = ActivityUpdateUserBinding.inflate(layoutInflater)
        val view = updateUserBinding.root
        setContentView(view)
    }
}