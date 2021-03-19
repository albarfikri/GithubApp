package com.example.github

import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridLayout
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.databinding.ActivityMainBinding
import com.scwang.wave.MultiWaveHeader

class MainActivity : AppCompatActivity() {
    private lateinit var lvUser: ListView
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var username: Array<String>
    private lateinit var name: Array<String>
    private lateinit var location: Array<String>
    private lateinit var repository: TypedArray
    private lateinit var company: Array<String>
    private lateinit var followers: TypedArray
    private lateinit var following: TypedArray
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

            Toast.makeText(this@MainActivity, users[position].name, Toast.LENGTH_SHORT).show()
        }
    }


    private fun prepare() {
        username = resources.getStringArray(R.array.username)
        name = resources.getStringArray(R.array.name)
        location = resources.getStringArray(R.array.location)
        repository = resources.obtainTypedArray(R.array.repository)
        company = resources.getStringArray(R.array.company)
        followers = resources.obtainTypedArray(R.array.followers)
        following = resources.obtainTypedArray(R.array.following)
        avatar = resources.obtainTypedArray(R.array.avatar)
    }


    private fun addItem() {
        for (position in name.indices) {
            val user = User(
                username[position],
                name[position],
                location[position],
                repository.getResourceId(position, -1),
                company[position],
                followers.getResourceId(position, -1),
                following.getResourceId(position, -1),
                avatar.getResourceId(position, -1)
            )
            users.add(user)
        }
        adapter.users = users
    }
}