package com.example.warehousemanager.ui.activities.avatar

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.example.warehousemanager.R
import com.example.warehousemanager.databinding.ActivitySetAvatarBinding
import com.example.warehousemanager.preferences.UserPreference
import com.example.warehousemanager.ui.activities.main.MainActivity
import com.github.dhaval2404.imagepicker.ImagePicker

class SetAvatarActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetAvatarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetAvatarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userPreference = UserPreference(this)

        binding.avatarIv.setImageDrawable(
            AvatarGenerator.AvatarBuilder(this)
                .setLabel("Minh")
                .setAvatarSize(340)
                .setTextSize(30)
                .toSquare()
                .toCircle()
                .setBackgroundColor(Color.RED)
                .build()
        )

        binding.chooseAvatarBtn.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.okBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.editAvatarBtn.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val uri: Uri = data?.data!!

                binding.avatarIv.setImageURI(uri)

                if (binding.chooseAvatarBtn.visibility == android.view.View.VISIBLE) {
                    binding.chooseAvatarBtn.visibility = android.view.View.GONE
                    binding.okBtn.visibility = android.view.View.VISIBLE
                    binding.editAvatarBtn.visibility = android.view.View.VISIBLE
                    binding.nextBtn.visibility = android.view.View.GONE
                }

            }

            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }

            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}