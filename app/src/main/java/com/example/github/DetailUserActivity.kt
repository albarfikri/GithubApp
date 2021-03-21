package com.example.github

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.github.databinding.ActivityMainBinding
import com.example.github.databinding.DetailUserBinding

class DetailUserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: DetailUserBinding

    companion object {
        const val dataUser = "data_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(dataUser) as User
        binding.tvName.text = user.name
        binding.tvCompany.text = user.company
        binding.tvLocation.text = user.location
        binding.tvFollower.text = user.followers
        binding.tvFollowing.text = user.following
        binding.tvRepository.text = user.repository
        binding.imgItemPhoto.setImageResource(user.avatar)
        binding.backButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back_button -> {
                val move = Intent(this@DetailUserActivity, MainActivity::class.java)
                startActivity(move)
            }
        }
    }

}