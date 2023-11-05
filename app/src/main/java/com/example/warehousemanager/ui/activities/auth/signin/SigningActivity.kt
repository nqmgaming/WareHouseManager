package com.example.warehousemanager.ui.activities.auth.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.example.warehousemanager.database.dao.WareHouseDAO
import com.example.warehousemanager.database.dao.WareHouseKeeperDAO
import com.example.warehousemanager.databinding.ActivitySigninBinding
import com.example.warehousemanager.preferences.PreferencesApp
import com.example.warehousemanager.preferences.UserPreference
import com.example.warehousemanager.ui.activities.auth.AuthActivity
import com.example.warehousemanager.ui.activities.auth.signup.SignupActivity
import com.example.warehousemanager.ui.activities.main.MainActivity

class SigningActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var wareHouseKeeperDAO: WareHouseKeeperDAO
    private lateinit var wareHouseDAO: WareHouseDAO
    val regex = Regex(pattern = "[a-z0-9]+")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesApp = PreferencesApp(this)
        if (preferencesApp.getIsLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding = ActivitySigninBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener {
            //intent
            startActivity(Intent(this, AuthActivity::class.java))
        }

        binding.signupLl.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.emailTil.helperText = "Email is required"
                    binding.signInBtn.isEnabled = false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    binding.emailTil.helperText = "Email is invalid"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.emailTil.helperText = null
                    binding.signInBtn.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmpty()) {
                    binding.emailTil.helperText = "Email is required"
                    binding.signInBtn.isEnabled = false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    binding.emailTil.helperText = "Email is invalid"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.emailTil.helperText = null
                    binding.signInBtn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    binding.emailTil.helperText = "Email is required"
                    binding.signInBtn.isEnabled = false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    binding.emailTil.helperText = "Email is invalid"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.emailTil.helperText = null
                    binding.signInBtn.isEnabled = true
                }
            }
        })

        binding.linkWarehouseEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is required"
                    binding.signInBtn.isEnabled = false
                } else if (!regex.matches(s.toString())) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is invalid"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.linkWarehouseTil.helperText = null
                    binding.signInBtn.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmpty()) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is required"
                    binding.signInBtn.isEnabled = false
                } else if (!regex.matches(s.toString())) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is invalid"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.linkWarehouseTil.helperText = null
                    binding.signInBtn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is required"
                    binding.signInBtn.isEnabled = false
                } else if (!regex.matches(s.toString())) {
                    binding.linkWarehouseTil.helperText = "Link warehouse is invalid"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.linkWarehouseTil.helperText = null
                    binding.signInBtn.isEnabled = true
                }
            }
        })

        binding.passwordEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.passwordTil.helperText = "Password is required"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.passwordTil.helperText = null
                    binding.signInBtn.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmpty()) {
                    binding.passwordTil.helperText = "Password is required"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.passwordTil.helperText = null
                    binding.signInBtn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    binding.passwordTil.helperText = "Password is required"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.passwordTil.helperText = null
                    binding.signInBtn.isEnabled = true
                }
            }
        })

        binding.signInBtn.setOnClickListener {
            val email = binding.emailEt.text.toString().trim().lowercase()
            val password = binding.passwordEt.text.toString().trim()
            val linkWarehouse = binding.linkWarehouseEt.text.toString().trim().lowercase()

            //check if link warehouse is empty
            if (linkWarehouse.isEmpty()) {
                binding.linkWarehouseTil.helperText = "Link warehouse is required"
                binding.signInBtn.isEnabled = false
                return@setOnClickListener
            }

            //regax for link warehouse only allow a-z, 0-9

            if (!regex.matches(linkWarehouse)) {
                binding.linkWarehouseTil.helperText = "Link warehouse is invalid"
                binding.signInBtn.isEnabled = false
                return@setOnClickListener
            }

            //check warehouse is not exist
            wareHouseDAO = WareHouseDAO(this)
            if (!wareHouseDAO.isWarehouseExist(linkWarehouse)) {
                binding.linkWarehouseTil.helperText = "Link warehouse is not exist"
                binding.signInBtn.isEnabled = false
                return@setOnClickListener
            }

            //check if email is empty
            if (email.isEmpty()) {
                binding.emailTil.helperText = "Email is required"
                binding.signInBtn.isEnabled = false
                return@setOnClickListener
            }
            //check if email is valid
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailTil.helperText = "Email is invalid"
                binding.signInBtn.isEnabled = false
                return@setOnClickListener
            }

            //check if email is exist
            wareHouseKeeperDAO = WareHouseKeeperDAO(this)
            if (!wareHouseKeeperDAO.isExistWarehouseKeeper(email)) {
                binding.emailTil.helperText = "Email is not exist"
                binding.signInBtn.isEnabled = false
                return@setOnClickListener
            }


            //check if password is empty
            if (password.isEmpty()) {
                binding.passwordTil.helperText = "Password is required"
                binding.signInBtn.isEnabled = false
                return@setOnClickListener
            }

            //check if password is correct
            if (!wareHouseKeeperDAO.isCorrectPassword(email, password)) {
                binding.passwordTil.helperText = "Password is incorrect"
                binding.signInBtn.isEnabled = false
                return@setOnClickListener
            }

            //check warehouse id not match with warehouse in keeper
            val warehouse = wareHouseDAO.getWarehouseById(linkWarehouse)
            val keeper = wareHouseKeeperDAO.getWarehouseKeeperById(email)

            if (warehouse!!.id != keeper!!.warehouseId) {
                binding.linkWarehouseTil.helperText = "Link warehouse is not match with email"
                binding.signInBtn.isEnabled = false
                return@setOnClickListener
            }

            val userPreference = UserPreference(this)
            userPreference.setWarehouse(linkWarehouse)
            userPreference.setEmail(email)



            Toast.makeText(this, "Signing in...", Toast.LENGTH_SHORT).show()

        }

    }
}