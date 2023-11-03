package com.example.warehousemanager.database.models

data class Product(
    val id: String,
    val imgUrl: String,
    val name: String,
    val description: String,
    val price: Double,
    val cost: Double,
    val inventory: Int,
    val position: String,
    val weight: Double,
    val status: Int,
    val supplierId: String, // Reference to the corresponding supplier
    val productCategoryId: String // Reference to the corresponding product category
)
