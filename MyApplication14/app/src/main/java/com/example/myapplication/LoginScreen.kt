package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityLoginScreenBinding
import com.example.myapplication.databinding.ActivityMainBinding

class LoginScreen : AppCompatActivity() {

    private val binding by lazy{ActivityLoginScreenBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.txt.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            if (binding.save.isChecked) {
                editor.putString("user", binding.user.text.toString())
                editor.putString("pass", binding.pass.text.toString())
                editor.putBoolean("token", true)
                editor.apply()
                startActivity(Intent(this, HomeScreen::class.java))
            }
        }
    }
}