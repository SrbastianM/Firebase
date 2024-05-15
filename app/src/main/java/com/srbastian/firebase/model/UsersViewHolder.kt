package com.srbastian.firebase.model

import androidx.recyclerview.widget.RecyclerView
import com.srbastian.firebase.databinding.UsersItemBinding

class UsersViewHolder(binding: UsersItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val name = binding.tvName
    val age = binding.tvAge
    val email = binding.tvEmail
    val layout = binding.linearLayout

    fun render(usersModel: Users) {
        name.text = usersModel.userName
        age.text = usersModel.userAge.toString()
        email.text = usersModel.userEmail
    }
}
