package com.example.warehousemanager.database.models

data class PurchaseOrderDetail(
    val id: String,
    val purchaseOrderId: String, // Reference to the corresponding purchase order
    val productId: String, // Reference to the corresponding product
    val quantity: Int,
    val pricePerUnit: Double
)
