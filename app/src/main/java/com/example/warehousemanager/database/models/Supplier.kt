package com.example.warehousemanager.database.models

data class Supplier(
    val id: Int,
    val name: String,
    val contactName: String,
    val contactPhone: String,
    val contactEmail: String,
    val address: String
)
