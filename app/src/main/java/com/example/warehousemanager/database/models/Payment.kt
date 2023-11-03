package com.example.warehousemanager.database.models

data class Payment(
    val id: String,
    val orderId: String, // Reference to the corresponding sales order
    val paymentMethod: String,
    val paymentDate: String, // You might want to use a proper date type here
    val amount: Double
)
