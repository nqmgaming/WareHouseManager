package com.example.warehousemanager.ui.activities.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.warehousemanager.R
import com.example.warehousemanager.adapter.SlideAdapter
import com.example.warehousemanager.databinding.ActivityOnboardingBinding
import com.example.warehousemanager.preferences.PreferencesApp
import com.example.warehousemanager.ui.activities.auth.AuthActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var slideAdapter: SlideAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isFirstTime = PreferencesApp(this).getFirstTime()

        if (!isFirstTime) {

            startActivity(Intent(this, AuthActivity::class.java))
            finish()

        }
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        slideAdapter = SlideAdapter(this)
        binding.viewPager.adapter = slideAdapter
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.currentItem = 0
        binding.viewPager.clipToPadding = false

        binding.indicator.setViewPager(binding.viewPager)

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem + 1 < slideAdapter.count) {
                binding.viewPager.currentItem += 1
            } else {
                PreferencesApp(this).setFirstTime(false)
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }

        binding.btnSkip.setOnClickListener {
            PreferencesApp(this).setFirstTime(false)
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        binding.viewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                if (position + 1 == slideAdapter.count) {
                    //set string resource for last page
                    binding.btnNext.text = getString(R.string.get_started)
                } else {
                    binding.btnNext.text = getString(R.string.next)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}