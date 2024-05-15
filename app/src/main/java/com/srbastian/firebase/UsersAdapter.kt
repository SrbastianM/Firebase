package com.srbastian.firebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srbastian.firebase.databinding.UsersItemBinding

class UsersAdapter(
    var context: Context,
    var userList: ArrayList<Users>,
) : RecyclerView.Adapter<UsersViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): UsersViewHolder {
        val view = UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(
        holder: UsersViewHolder,
        position: Int,
    ) {
        val item = userList[position]
        holder.render(item)

        holder.layout.setOnClickListener {
            val intent = Intent(context, UpdateUser::class.java)
            intent.putExtra("id", userList[position].userId)
            intent.putExtra("userName", userList[position].userName)
            intent.putExtra("userAge", userList[position].userAge)
            intent.putExtra("userEmail", userList[position].userEmail)
            context.startActivity(intent)
        }
    }

    fun getUserId(position: Int): String {
        return userList[position].userId
    }
}
