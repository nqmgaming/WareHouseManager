package com.example.warehousemanager.database.models

data class SalesOrderDetail(
    val id: String,
    val salesOrderId: String, // Reference to the corresponding sales order
    val productId: String, // Reference to the corresponding product
    val quantity: Int,
    val pricePerUnit: Double
)
