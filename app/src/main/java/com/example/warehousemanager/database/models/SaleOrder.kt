package com.example.warehousemanager.database.models

data class SalesOrder(
    val id: String,
    val storeId: String, // Reference to the corresponding store
    val shippingCarrierId: String, // Reference to the corresponding shipping carrier
    val orderDate: String, // You might want to use a proper date type here
    val status: String,
    val totalAmount: Double
)
