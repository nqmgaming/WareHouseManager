package com.example.warehousemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.warehousemanager.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesApp = PreferencesApp(this)
        if (preferencesApp.getIsLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signInWithEmailTv.setOnClickListener {
            startActivity(Intent(this, SigningActivity::class.java))
        }

        binding.signUpWithEmailTv.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}