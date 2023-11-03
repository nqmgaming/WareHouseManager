package com.example.warehousemanager.database.models

data class ShippingCarrier(
    val id: String,
    val name: String,
    val contactName: String,
    val contactEmail: String,
    val contactPhone: String,
    val address: String
)
