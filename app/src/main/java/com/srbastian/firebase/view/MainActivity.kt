package com.srbastian.firebase.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.srbastian.firebase.R
import com.srbastian.firebase.adapter.UsersAdapter
import com.srbastian.firebase.databinding.ActivityMainBinding
import com.srbastian.firebase.model.Users

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

//        toolbarBinding = ToolbarBinding.inflate(layoutInflater)
// //        mainBinding.root.addView(toolbarBinding.root)
//

        mainBinding.fabId.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
        // Delete User drag to left or right
        ItemTouchHelper(
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int,
                ) {
                    val id = usersAdapter.getUserId(viewHolder.adapterPosition)
                    myReference.child(id).removeValue()

                    Toast.makeText(applicationContext, "User was deleted", Toast.LENGTH_SHORT)
                        .show()
                }
            },
        ).attachToRecyclerView(mainBinding.rvMainActivity)
        retrieveDataFromDatabase()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.iSignOut) {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {
        Toast.makeText(applicationContext, "test", Toast.LENGTH_SHORT).show()
    }

    private fun retrieveDataFromDatabase() {
        myReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    userList.clear()

                    for (eachUser in snapshot.children) {
                        val user = eachUser.getValue(Users::class.java)
                        if (user != null) {
                            userList.add(user)
                            println(userList)
                        }
                        usersAdapter = UsersAdapter(this@MainActivity, userList)

                        mainBinding.rvMainActivity.layoutManager =
                            LinearLayoutManager(this@MainActivity)
                        mainBinding.rvMainActivity.adapter = usersAdapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}
