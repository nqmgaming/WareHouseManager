package com.example.warehousemanager.ui.activities.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.warehousemanager.database.dao.WareHouseDAO
import com.example.warehousemanager.database.dao.WareHouseKeeperDAO
import com.example.warehousemanager.database.models.Warehouse
import com.example.warehousemanager.database.models.WarehouseKeeper
import com.example.warehousemanager.databinding.ActivitySignupDetailsInfoBinding
import com.example.warehousemanager.preferences.PreferencesApp
import com.example.warehousemanager.preferences.UserPreference
import com.example.warehousemanager.ui.activities.avatar.SetAvatarActivity
import com.example.warehousemanager.ui.activities.main.MainActivity

class SignupDetailInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupDetailsInfoBinding
    private lateinit var userPreference: UserPreference

    private lateinit var warehouse: Warehouse
    private lateinit var wareHouseDAO: WareHouseDAO
    private lateinit var warehouseKeeper: WarehouseKeeper
    private lateinit var warehouseKeeperDAO: WareHouseKeeperDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesApp = PreferencesApp(this)
        if (preferencesApp.getIsLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding = ActivitySignupDetailsInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        userPreference = UserPreference(this)



        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.mapTil.setEndIconOnClickListener {
            Intent(this, PickAddressActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.fullNameEt.addTextChangedListener {
            validateFullName(it)
        }
        binding.mapEt.setText(userPreference.getAddress().name)
        binding.mapEt.addTextChangedListener { validateAddress(it) }
        binding.phoneEt.addTextChangedListener { validatePhoneNumber(it) }
        binding.linkWarehouseEt.addTextChangedListener { validateLinkWarehouse(it) }
        binding.emailEt.addTextChangedListener { validateEmail(it) }
        binding.passwordEt.addTextChangedListener { validatePassword(it) }

        binding.continueBtn.setOnClickListener {
            if (!validateAllFields()) {
                binding.continueBtn.isEnabled = false
            } else {
                val fullName = binding.fullNameEt.text.toString().trim()
                val phoneNumber = binding.phoneEt.text.toString().trim()
                val linkWarehouse = binding.linkWarehouseEt.text.toString().trim()
                val email = binding.emailEt.text.toString().trim()
                val password = binding.passwordEt.text.toString().trim()

                warehouse = Warehouse(
                    id = linkWarehouse,
                    name = null,
                    province = userPreference.getAddress().id,
                    representative = fullName,
                    phoneNumber = phoneNumber
                )

                warehouseKeeper = WarehouseKeeper(
                    id = email,
                    password = password,
                    name = fullName,
                    email = email,
                    phoneNumber = phoneNumber,
                    birthday = null,
                    warehouseId = linkWarehouse,
                    avatar = null
                )

                wareHouseDAO = WareHouseDAO(this)
                warehouseKeeperDAO = WareHouseKeeperDAO(this)

                if (wareHouseDAO.isWarehouseExist(linkWarehouse)) {
                    Toast.makeText(this, "Warehouse is exist", Toast.LENGTH_SHORT).show()
                    binding.linkWarehouseTil.helperText = "Warehouse is exist"
                    return@setOnClickListener
                }

                if (warehouseKeeperDAO.isExistWarehouseKeeper(email)) {
                    Toast.makeText(this, "Warehouse keeper is exist", Toast.LENGTH_SHORT).show()
                    binding.emailTil.helperText = "Email is exist"
                    return@setOnClickListener
                }

                if (wareHouseDAO.insertWarehouse(warehouse) && warehouseKeeperDAO.insertWarehouseKeeper(warehouseKeeper)) {
                    preferencesApp.setIsLoggedIn(true)
                    Toast.makeText(this, "Sign up successfully", Toast.LENGTH_SHORT).show()
                    Intent(this, SetAvatarActivity::class.java).apply {
                        startActivity(this)
                    }
                } else {
                    Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun validateFullName(s: Editable?) {
        if (s.toString().isEmpty()) {
            binding.fullNameTil.helperText = "Full name is required"
            binding.continueBtn.isEnabled = false
        } else {
            binding.fullNameTil.helperText = null
            binding.continueBtn.isEnabled = true
            userPreference.setFullName(s.toString())
        }
    }

    private fun validateAddress(s: Editable?) {
        if (s.toString().isEmpty()) {
            binding.mapTil.helperText = "Address is required"
            binding.continueBtn.isEnabled = false
        } else {
            binding.mapTil.helperText = null
            binding.continueBtn.isEnabled = true
        }
    }

    private fun validatePhoneNumber(s: Editable?) {
        if (s.toString().isEmpty()) {
            binding.phoneTil.helperText = "Phone number is required"
            binding.continueBtn.isEnabled = false
        } else if (s.toString().length < 9) {
            binding.phoneTil.helperText = "Phone number is invalid"
            binding.continueBtn.isEnabled = false
        } else {
            binding.phoneTil.helperText = null
            binding.continueBtn.isEnabled = true
            userPreference.setPhoneNumber(s.toString())
        }
    }

    private fun validateLinkWarehouse(s: Editable?) {
        wareHouseDAO = WareHouseDAO(this)
        if (s.toString().isEmpty()) {
            binding.linkWarehouseTil.helperText = "Link warehouse is required"
            binding.continueBtn.isEnabled = false
        } else if (wareHouseDAO.isWarehouseExist(s.toString())) {
            binding.linkWarehouseTil.helperText = "Warehouse is exist"
            binding.continueBtn.isEnabled = false

        } else {
            binding.linkWarehouseTil.helperText = null
            binding.continueBtn.isEnabled = true
            userPreference.setLinkWarehouse(s.toString())
        }
    }

    private fun validateEmail(s: Editable?) {
        warehouseKeeperDAO = WareHouseKeeperDAO(this)
        if (s.toString().isEmpty()) {
            binding.emailTil.helperText = "Email is required"
            binding.continueBtn.isEnabled = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
            binding.emailTil.helperText = "Email is invalid"
            binding.continueBtn.isEnabled = false
        } else if (warehouseKeeperDAO.isExistWarehouseKeeper(s.toString())) {
            binding.emailTil.helperText = "Email is exist"
            binding.continueBtn.isEnabled = false
        } else {
            binding.emailTil.helperText = null
            binding.continueBtn.isEnabled = true
            userPreference.setEmail(s.toString())
        }
    }

    private fun validatePassword(s: Editable?) {
        if (s.toString().isEmpty()) {
            binding.passwordTil.helperText = "Password is required"
            binding.continueBtn.isEnabled = false
        } else if (s.toString().length < 8) {
            binding.passwordTil.helperText = "Password must be at least 8 characters"
            binding.continueBtn.isEnabled = false
        } else {
            binding.passwordTil.helperText = null
            binding.continueBtn.isEnabled = true
            userPreference.setPassword(s.toString())
        }
    }

    private fun validateAllFields(): Boolean {
        val fullName = binding.fullNameEt.text.toString().trim()
        val address = binding.mapEt.text.toString().trim()
        val phoneNumber = binding.phoneEt.text.toString().trim()
        val linkWarehouse = binding.linkWarehouseEt.text.toString().trim()
        val email = binding.emailEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()

        if (fullName.isEmpty()) {
            binding.fullNameTil.helperText = "Full name is required"
            binding.continueBtn.isEnabled = false
        } else if (address.isEmpty()) {
            binding.mapTil.helperText = "Address is required"
            binding.continueBtn.isEnabled = false
        } else if (phoneNumber.isEmpty()) {
            binding.phoneTil.helperText = "Phone number is required"
            binding.continueBtn.isEnabled = false
        } else if (phoneNumber.length < 9) {
            binding.phoneTil.helperText = "Phone number is invalid"
            binding.continueBtn.isEnabled = false
        } else if (linkWarehouse.isEmpty()) {
            binding.linkWarehouseTil.helperText = "Link warehouse is required"
            binding.continueBtn.isEnabled = false
        } else if (email.isEmpty()) {
            binding.emailTil.helperText = "Email is required"
            binding.continueBtn.isEnabled = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailTil.helperText = "Email is invalid"
            binding.continueBtn.isEnabled = false
        } else if (password.isEmpty()) {
            binding.passwordTil.helperText = "Password is required"
            binding.continueBtn.isEnabled = false
        } else if (password.length < 8) {
            binding.passwordTil.helperText = "Password must be at least 8 characters"
            binding.continueBtn.isEnabled = false
        } else {
            binding.fullNameTil.helperText = null
            binding.mapTil.helperText = null
            binding.phoneTil.helperText = null
            binding.linkWarehouseTil.helperText = null
            binding.emailTil.helperText = null
            binding.passwordTil.helperText = null
            binding.continueBtn.isEnabled = true

            return true

        }

        return false
    }

    override fun onResume() {
        super.onResume()
        val userPreference = UserPreference(this)
        binding.fullNameEt.setText(userPreference.getFullName())
        binding.mapEt.setText(userPreference.getAddress().name)
        binding.phoneEt.setText(userPreference.getPhoneNumber())
        binding.linkWarehouseEt.setText(userPreference.getLinkWarehouse())
        binding.emailEt.setText(userPreference.getEmail())
    }

    override fun onDestroy() {
        super.onDestroy()
        val preferencesApp = UserPreference(this)
        preferencesApp.clearPreferences()
    }
}