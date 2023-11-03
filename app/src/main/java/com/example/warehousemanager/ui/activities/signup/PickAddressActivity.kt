package com.example.warehousemanager.ui.activities.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehousemanager.R
import com.example.warehousemanager.adapter.ProvinceAdapter
import com.example.warehousemanager.database.dao.ProvinceDAO
import com.example.warehousemanager.database.models.Province
import com.example.warehousemanager.databinding.ActivityPickAddressBinding

class PickAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPickAddressBinding
    private lateinit var provinceList: ArrayList<Province>
    private lateinit var provinceAdapter: ProvinceAdapter
    private lateinit var provinceDAO: ProvinceDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickAddressBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, SignupDetailsInfoActivity::class.java))
            finish()
        }

        binding.provinceRv.setHasFixedSize(true)
        binding.provinceRv.layoutManager = LinearLayoutManager(this)

        provinceDAO = ProvinceDAO(this)
        provinceList = provinceDAO.getAllProvinces()

        if (provinceList.size > 0) {
            provinceAdapter = ProvinceAdapter(this, provinceList)
            binding.provinceRv.adapter = provinceAdapter
            provinceAdapter.notifyDataSetChanged()
        }
    }
}