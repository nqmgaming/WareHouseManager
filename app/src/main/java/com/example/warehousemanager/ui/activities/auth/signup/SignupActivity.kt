package com.example.warehousemanager.ui.activities.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.example.warehousemanager.database.dao.WareHouseKeeperDAO
import com.example.warehousemanager.database.models.WarehouseKeeper
import com.example.warehousemanager.databinding.ActivitySignupBinding
import com.example.warehousemanager.preferences.PreferencesApp
import com.example.warehousemanager.preferences.UserPreference
import com.example.warehousemanager.ui.activities.auth.AuthActivity
import com.example.warehousemanager.ui.activities.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var warehouseKeeper: WarehouseKeeper
    lateinit var wareHouseKeeperDAO: WareHouseKeeperDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesApp = PreferencesApp(this)
        if (preferencesApp.getIsLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
        wareHouseKeeperDAO = WareHouseKeeperDAO(this)
        binding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.emailTil.helperText = "Email is required"
                    binding.continueBtn.isEnabled = false
                } else if (wareHouseKeeperDAO.isExistWarehouseKeeper(s.toString())) {
                    binding.emailTil.helperText = "Email already exists"
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
                } else if (wareHouseKeeperDAO.isExistWarehouseKeeper(s.toString())) {
                    binding.emailTil.helperText = "Email already exists"
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
                } else if (wareHouseKeeperDAO.isExistWarehouseKeeper(s.toString())) {
                    binding.emailTil.helperText = "Email already exists"
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

        binding.continueBtn.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            if (email.isEmpty()) {
                binding.emailTil.helperText = "Email is required"
                binding.continueBtn.isEnabled = false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailTil.helperText = "Email is invalid"
                binding.continueBtn.isEnabled = false
            } else {
                binding.emailTil.helperText = null
                binding.continueBtn.isEnabled = true
                val userPreference = UserPreference(this)
                userPreference.setEmail(email.lowercase())
                Toast.makeText(this, "Verification link sent to $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SignupDetailInfoActivity::class.java))
                finish()

//                //action code
//                val actionCodeSettings = ActionCodeSettings.newBuilder()
//                    .setUrl("https://warehousemanager-7ccf3.firebaseapp.com")
//                    .setHandleCodeInApp(true)
//                    .setAndroidPackageName(
//                        "com.example.warehousemanager",
//                        true,
//                        "10"
//                    )
//                    .build()
//
//                //validate email and send verification link
//                auth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
//                    if (it.result?.signInMethods?.size == 0) {
//                        auth.sendSignInLinkToEmail(email, actionCodeSettings).addOnCompleteListener {
//                            if (it.isSuccessful) {
//                                preferencesApp.setEmail(email)
//                                Toast.makeText(this, "Verification link sent to $email", Toast.LENGTH_SHORT).show()
//                                startActivity(Intent(this, SignupDetailsInfoActivity::class.java))
//                                finish()
//                            } else {
//                                Toast.makeText(this, "Error: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
//                                Log.d("sss", "onCreate: ${it.exception?.message}")
//                            }
//                        }
//                    } else {
//                        binding.emailTil.helperText = "Email already exists"
//                        binding.continueBtn.isEnabled = false
//                        Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show()
//                    }
//                }
            }
        }
    }
}