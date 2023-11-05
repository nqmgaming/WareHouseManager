package com.example.warehousemanager.ui.activities.auth.forgot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import com.example.warehousemanager.database.dao.WareHouseDAO
import com.example.warehousemanager.database.dao.WareHouseKeeperDAO
import com.example.warehousemanager.databinding.ActivityForgotPasswordBinding
import com.example.warehousemanager.ui.activities.auth.signin.SigningActivity

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    private lateinit var wareHouseKeeperDAO: WareHouseKeeperDAO
    private lateinit var wareHouseDAO: WareHouseDAO

    val regex = Regex(pattern = "[a-z0-9]+")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener {
           startActivity(Intent(this, SigningActivity::class.java))
        }

        binding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.emailTil.helperText = "Email is required"
                    binding.continueBtn.isEnabled = false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    binding.emailTil.helperText = "Email is invalid"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.emailTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmpty()) {
                    binding.emailTil.helperText = "Email is required"
                    binding.continueBtn.isEnabled = false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    binding.emailTil.helperText = "Email is invalid"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.emailTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    binding.emailTil.helperText = "Email is required"
                    binding.continueBtn.isEnabled = false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    binding.emailTil.helperText = "Email is invalid"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.emailTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }
        })

        binding.linkWarehouseEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is required"
                    binding.continueBtn.isEnabled = false
                } else if (!regex.matches(s.toString())) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is invalid"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.linkWarehouseTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmpty()) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is required"
                    binding.continueBtn.isEnabled = false
                } else if (!regex.matches(s.toString())) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is invalid"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.linkWarehouseTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is required"
                    binding.continueBtn.isEnabled = false
                } else if (!regex.matches(s.toString())) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is invalid"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.linkWarehouseTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }
        })

        binding.continueBtn.setOnClickListener{
            val linkWarehouse = binding.linkWarehouseEt.text.toString().trim().lowercase()
            val email = binding.emailEt.text.toString().trim().lowercase()


            //check if link warehouse is empty
            if (linkWarehouse.isEmpty()) {
                binding.linkWarehouseTil.helperText = "Link warehouse is required"
                binding.continueBtn.isEnabled = false
                return@setOnClickListener
            }

            //regex for link warehouse only allow a-z, 0-9

            if (!regex.matches(linkWarehouse)) {
                binding.linkWarehouseTil.helperText = "Link warehouse is invalid"
                binding.continueBtn.isEnabled = false
                return@setOnClickListener
            }

            //check warehouse is not exist
            wareHouseDAO = WareHouseDAO(this)
            if (!wareHouseDAO.isWarehouseExist(linkWarehouse)) {
                binding.linkWarehouseTil.helperText = "Link warehouse is not exist"
                binding.continueBtn.isEnabled = false
                return@setOnClickListener
            }

            //check if email is empty
            if (email.isEmpty()) {
                binding.emailTil.helperText = "Email is required"
                binding.continueBtn.isEnabled = false
                return@setOnClickListener
            }
            //check if email is valid
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailTil.helperText = "Email is invalid"
                binding.continueBtn.isEnabled = false
                return@setOnClickListener
            }

            //check if email is exist
            wareHouseKeeperDAO = WareHouseKeeperDAO(this)
            if (!wareHouseKeeperDAO.isExistWarehouseKeeper(email)) {
                binding.emailTil.helperText = "Email is not exist"
                binding.continueBtn.isEnabled = false
                return@setOnClickListener
            }


            //check warehouse id not match with warehouse in keeper
            val warehouse = wareHouseDAO.getWarehouseById(linkWarehouse)
            val keeper = wareHouseKeeperDAO.getWarehouseKeeperById(email)

            if (warehouse!!.id != keeper!!.warehouseId) {
                binding.linkWarehouseTil.helperText = "Link warehouse is not match with email"
                binding.continueBtn.isEnabled = false
                return@setOnClickListener
            }

            startActivity(Intent(this, SetPasswordActivity::class.java).apply {
                putExtra("email", email)
                putExtra("linkWarehouse", linkWarehouse)
            })
        }
    }
}