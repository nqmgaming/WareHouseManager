package com.example.warehousemanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.warehousemanager.R
import com.example.warehousemanager.databinding.SlideLayoutBinding

class SlideAdapter(private val context: Context) : PagerAdapter() {

    private val slideImages = intArrayOf(
        R.drawable.warehouse_4_0,
        R.drawable.warehouse_optimization,
        R.drawable.warehouse_control,
    )

    private val slideHeadings = arrayOf(
        "Warehouse Manager 4.0",
        "Warehouse Optimization",
        "Control Your Inventory Anytime, Anywhere",
    )


    private val slideDescriptions = arrayOf(
        "Transform your warehouse into a smart system with advanced AI technology.",
        "Save time and resources by automating warehouse management processes.",
        "Track inventory, orders, and deliveries with ease right from your mobile device.",
    )

    override fun getCount(): Int {
        return slideHeadings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val binding = SlideLayoutBinding.inflate(LayoutInflater.from(context), container, false)

        binding.slideIv.setImageResource(slideImages[position])
        binding.slideTitleTv.text = slideHeadings[position]
        binding.slideDescriptionTv.text = slideDescriptions[position]

        container.addView(binding.root)

        return binding.root

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}