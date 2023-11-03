package com.example.warehousemanager.ui.activities.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.warehousemanager.R
import com.example.warehousemanager.databinding.ActivityPickAddressBinding
import com.paulrybitskyi.persistentsearchview.listeners.OnSearchConfirmedListener
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate

class PickAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPickAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickAddressBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding.persistentSearchView){
            setOnLeftBtnClickListener {
                finish()
            }
            setOnClearInputBtnClickListener{
            }
            setVoiceRecognitionDelegate(VoiceRecognitionDelegate(this@PickAddressActivity))

            setSuggestionsDisabled(true)
        }
    }
}