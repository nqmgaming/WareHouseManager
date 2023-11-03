package com.example.warehousemanager.database.models

data class PurchaseOrder(
    val id: String,
    val supplierId: Int, // Reference to the corresponding supplier
    val shippingCarrierId: String, // Reference to the corresponding shipping carrier
    val orderDate: String, // You might want to use a proper date type here
    val status: String,
    val totalAmount: Double
)
