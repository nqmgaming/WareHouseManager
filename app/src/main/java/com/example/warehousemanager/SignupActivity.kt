package com.example.warehousemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.warehousemanager.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesApp = PreferencesApp(this)
        if (preferencesApp.getIsLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}