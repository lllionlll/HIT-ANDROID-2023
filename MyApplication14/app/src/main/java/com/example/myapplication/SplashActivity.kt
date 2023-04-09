package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            if (sharedPreference.getBoolean("token", false)) {
                startActivity(Intent(this, HomeScreen::class.java))
            } else {
                startActivity(Intent(this, LoginScreen::class.java))
            }
            finishAffinity()
        }, 1400)
    }
}