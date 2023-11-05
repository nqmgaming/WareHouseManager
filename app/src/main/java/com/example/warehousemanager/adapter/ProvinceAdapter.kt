package com.example.warehousemanager.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.warehousemanager.database.models.Province
import com.example.warehousemanager.databinding.ItemProvinceBinding
import com.example.warehousemanager.preferences.PreferencesApp
import com.example.warehousemanager.preferences.UserPreference
import com.example.warehousemanager.ui.activities.auth.signup.SignupDetailsInfoActivity

class ProvinceAdapter(
    private val context: Context,
    private val provincesList: ArrayList<Province>
) : RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {

    private val preferences: PreferencesApp = PreferencesApp(context)

    class ProvinceViewHolder(
        private val binding: ItemProvinceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            province: Province,
            preferences: UserPreference = UserPreference(binding.root.context)
        ) {
            binding.provinceTv.text = province.name

            binding.itemProvinceCl.setOnClickListener {
                preferences.setAddress(province)
                Toast.makeText(binding.root.context, province.name, Toast.LENGTH_SHORT).show()
                Intent(binding.root.context, SignupDetailsInfoActivity::class.java).also {
                    binding.root.context.startActivity(it)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val binding = ItemProvinceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProvinceViewHolder(binding)
    }

    override fun getItemCount() = provincesList.size

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bind(provincesList[position])
    }
}