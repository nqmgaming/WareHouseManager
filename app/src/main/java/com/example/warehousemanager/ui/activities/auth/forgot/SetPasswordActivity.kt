package com.example.warehousemanager.ui.activities.auth.forgot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.warehousemanager.R
import com.example.warehousemanager.database.dao.WareHouseKeeperDAO
import com.example.warehousemanager.database.models.WarehouseKeeper
import com.example.warehousemanager.databinding.ActivitySetPasswordBinding
import com.example.warehousemanager.ui.activities.auth.signin.SigningActivity

class SetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetPasswordBinding
    private lateinit var wareHouseKeeperDAO: WareHouseKeeperDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        //set toolbar
        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        binding.passwordEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.passwordTil.helperText = "Password is required"
                    binding.continueBtn.isEnabled = false
                } else if (binding.passwordEt.length() < 8) {
                    binding.passwordTil.helperText = "Password must be at least 8 characters"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.passwordTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmpty()) {
                    binding.passwordTil.helperText = "Password is required"
                    binding.continueBtn.isEnabled = false
                } else if (binding.passwordEt.length() < 8) {
                    binding.passwordTil.helperText = "Password must be at least 8 characters"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.passwordTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    binding.passwordTil.helperText = "Password is required"
                    binding.continueBtn.isEnabled = false
                } else if (binding.passwordEt.length() < 8) {
                    binding.passwordTil.helperText = "Password must be at least 8 characters"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.passwordTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }
        })

        binding.confirmPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.confirmPasswordTil.helperText = "Confirm password is required"
                    binding.continueBtn.isEnabled = false
                } else if (binding.confirmPasswordEt.text.toString() != binding.passwordEt.text.toString()) {
                    binding.confirmPasswordTil.helperText = "Confirm password is not match"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.confirmPasswordTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmpty()) {
                    binding.confirmPasswordTil.helperText = "Confirm password is required"
                    binding.continueBtn.isEnabled = false
                } else if (binding.confirmPasswordEt.text.toString() != binding.passwordEt.text.toString()) {
                    binding.confirmPasswordTil.helperText = "Confirm password is not match"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.confirmPasswordTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    binding.confirmPasswordTil.helperText = "Confirm password is required"
                    binding.continueBtn.isEnabled = false
                } else if (binding.confirmPasswordEt.text.toString() != binding.passwordEt.text.toString()) {
                    binding.confirmPasswordTil.helperText = "Confirm password is not match"
                    binding.continueBtn.isEnabled = false
                } else {
                    binding.confirmPasswordTil.helperText = null
                    binding.continueBtn.isEnabled = true
                }
            }
        })

        binding.continueBtn.setOnClickListener{
            val password = binding.passwordEt.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEt.text.toString().trim()

            if (password.isEmpty()){
                binding.passwordTil.helperText = "Password is required"
                binding.continueBtn.isEnabled = false
            } else if (password.length < 8){
                binding.passwordTil.helperText = "Password must be at least 8 characters"
                binding.continueBtn.isEnabled = false
            } else if (confirmPassword.isEmpty()){
                binding.confirmPasswordTil.helperText = "Confirm password is required"
                binding.continueBtn.isEnabled = false
            } else if (confirmPassword != password){
                binding.confirmPasswordTil.helperText = "Confirm password is not match"
                binding.continueBtn.isEnabled = false
            } else {
                binding.passwordTil.helperText = null
                binding.confirmPasswordTil.helperText = null
                binding.continueBtn.isEnabled = true
            }
            //get data from intent
            val email = intent.getStringExtra("email")
            wareHouseKeeperDAO = WareHouseKeeperDAO(this)
            if(wareHouseKeeperDAO.setNewPassword(email!!, password)){
                startActivity(Intent(this, SigningActivity::class.java))
                Toast.makeText(this, "Set new password successfully", Toast.LENGTH_SHORT).show()
            } else {
               Toast.makeText(this, "Set new password failed", Toast.LENGTH_SHORT).show()
            }
        }


    }
}