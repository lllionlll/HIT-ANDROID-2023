package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {

    private val binding by lazy { ActivityHomeScreenBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val  editor = sharedPreference.edit()
        val user = sharedPreference.getString("user", "null")
        val pass = sharedPreference.getString("pass", "null")
        binding.user.setText(user!!)
        binding.pass.setText(pass!!)
        binding.txt.setOnClickListener{
            editor.clear()
            editor.apply()
        }

    }
}