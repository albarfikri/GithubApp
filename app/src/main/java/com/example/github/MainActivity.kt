package com.example.github

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.github.R.array
import com.example.github.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var username: Array<String>
    private lateinit var name: Array<String>
    private lateinit var location: Array<String>
    private lateinit var repository: Array<String>
    private lateinit var company: Array<String>
    private lateinit var followers: Array<String>
    private lateinit var following: Array<String>
    private lateinit var avatar: TypedArray
    private var users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(this)
        binding.lvList.adapter = adapter

        prepare()
        addItem()

        binding.lvList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@MainActivity, "Loading...", Toast.LENGTH_SHORT).show()
            Timer().schedule(1000) {

                val userObject = User(
                    username[position],
                    name[position],
                    location[position],
                    repository[position],
                    company[position],
                    followers[position],
                    following[position],
                    avatar.getResourceId(position, -1)
                )

                val move = Intent(this@MainActivity, DetailUserActivity::class.java)
                move.putExtra(DetailUserActivity.dataUser, userObject)
                startActivity(move)
            }
        }
    }


    private fun prepare() {
        username = resources.getStringArray(array.username)
        name = resources.getStringArray(array.name)
        location = resources.getStringArray(array.location)
        repository = resources.getStringArray(array.repository)
        company = resources.getStringArray(array.company)
        followers = resources.getStringArray(array.followers)
        following = resources.getStringArray(array.following)
        avatar = resources.obtainTypedArray(array.avatar)
    }

    private fun addItem() {
        for (position in name.indices) {
            val user = User(
                username[position],
                name[position],
                location[position],
                repository[position],
                company[position],
                followers[position],
                following[position],
                avatar.getResourceId(position, -1)
            )
            users.add(user)
        }
        adapter.users = users
    }
}