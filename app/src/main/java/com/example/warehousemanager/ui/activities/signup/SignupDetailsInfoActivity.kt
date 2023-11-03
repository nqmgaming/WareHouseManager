package com.example.warehousemanager.ui.activities.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.warehousemanager.R
import com.example.warehousemanager.databinding.ActivitySignupDetailsInfoBinding

class SignupDetailsInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupDetailsInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupDetailsInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mapTil.setEndIconOnClickListener{
            Intent(this, PickAddressActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}