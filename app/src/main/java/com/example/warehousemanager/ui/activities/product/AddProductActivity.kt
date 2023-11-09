package com.example.warehousemanager.ui.activities.product

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehousemanager.adapter.ImageProductAdapter
import com.example.warehousemanager.databinding.ActivityAddProductBinding
import com.github.dhaval2404.imagepicker.ImagePicker
class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private var listImage = mutableListOf<Uri>()
    private lateinit var imageProductAdapter: ImageProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // RecyclerView setup
        imageProductAdapter = ImageProductAdapter(this, listImage)
        binding.imageProductRv.apply {
            layoutManager = LinearLayoutManager(this@AddProductActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = imageProductAdapter
        }

        binding.addPhotoIv.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val uri: Uri = data?.data!!
                listImage.add(uri)
                imageProductAdapter.notifyDataSetChanged()
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

