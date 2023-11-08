package com.example.warehousemanager.ui.activities.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.warehousemanager.databinding.ActivityAddProductBinding

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}