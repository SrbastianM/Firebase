package com.srbastian.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srbastian.firebase.databinding.UsersItemBinding

class UsersAdapter(
    var context: Context,
    var userList: ArrayList<Users>
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    inner class UsersViewHolder(val adapterBinding: UsersItemBinding) :
        RecyclerView.ViewHolder(adapterBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.adapterBinding.tvName.text = userList[position].userName
        holder.adapterBinding.tvAge.text = userList[position].userAge.toString()
        holder.adapterBinding.tvEmail.text = userList[position].userEmail
    }
}