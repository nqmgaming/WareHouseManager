package com.example.warehousemanager.ui.activities.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehousemanager.adapter.OnProvinceSelectedListener
import com.example.warehousemanager.adapter.ProvinceAdapter
import com.example.warehousemanager.database.dao.ProvinceDAO
import com.example.warehousemanager.database.models.Province
import com.example.warehousemanager.databinding.ActivityPickAddressBinding

class PickAddressActivity : AppCompatActivity() , OnProvinceSelectedListener {
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
            startActivity(Intent(this, SignupDetailInfoActivity::class.java))
            finish()
        }

        binding.provinceRv.setHasFixedSize(true)
        binding.provinceRv.layoutManager = LinearLayoutManager(this)

        provinceDAO = ProvinceDAO(this)
        provinceList = provinceDAO.getAllProvinces()

        if (provinceList.size > 0) {
            provinceAdapter = ProvinceAdapter(this, provinceList, this)
            binding.provinceRv.adapter = provinceAdapter
            provinceAdapter.notifyDataSetChanged()
        }

        binding.searchEt.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                if (s.toString().trim().isNotEmpty()) {
                    val filteredList = ArrayList<Province>()
                    for (province in provinceList) {
                        if (province.name.lowercase().contains(s.toString().lowercase())) {
                            filteredList.add(province)
                        }
                    }
                    refreshList(filteredList)

                } else {
                    refreshList(provinceList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isNotEmpty()) {
                    val filteredList = ArrayList<Province>()
                    for (province in provinceList) {
                        if (province.name.lowercase().contains(s.toString().lowercase())) {
                            filteredList.add(province)
                        }
                    }
                    refreshList(filteredList)

                } else {
                    refreshList(provinceList)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isNotEmpty()) {
                    val filteredList = ArrayList<Province>()
                    for (province in provinceList) {
                        if (province.name.lowercase().contains(s.toString().lowercase())) {
                            filteredList.add(province)
                        }
                    }
                    refreshList(filteredList)

                } else {
                    refreshList(provinceList)
                }
            }
        })
    }

    fun refreshList(list: ArrayList<Province>) {
        provinceAdapter = ProvinceAdapter(this, list, this)
        binding.provinceRv.adapter = provinceAdapter
        provinceAdapter.notifyDataSetChanged()

    }

    override fun onProvinceSelected(province: Province) {
        // Navigate to SignupDetailsInfoActivity
        Intent(this, SignupDetailInfoActivity::class.java).also { startActivity(it) }
        // Finish this activity
        finish()
    }
}