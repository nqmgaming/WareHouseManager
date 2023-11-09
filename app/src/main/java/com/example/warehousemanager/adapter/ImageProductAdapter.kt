package com.example.warehousemanager.adapter

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warehousemanager.databinding.ItemPhotoProductBinding

class ImageProductAdapter(
    private val context: Context,
    private val listImage: MutableList<Uri>
) : RecyclerView.Adapter<ImageProductAdapter.ImageProductViewHolder>() {

    inner class ImageProductViewHolder(private val binding: ItemPhotoProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uri: Uri) {
            binding.view.setImageURI(uri)
            binding.closeBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    showDeleteConfirmationDialog(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageProductAdapter.ImageProductViewHolder {
        val binding =
            ItemPhotoProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageProductAdapter.ImageProductViewHolder, position: Int) {
        holder.bind(listImage[position])
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    private fun showDeleteConfirmationDialog(position: Int) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.apply {
            setMessage("Are you sure you want to delete this image?")
            setPositiveButton("Yes") { dialog, _ ->
                listImage.removeAt(position)
                notifyDataSetChanged()
                dialog.dismiss()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            create().show()
        }
    }
}
