package com.example.warehousemanager.database.models

data class Warehouse(
    val id: String,
    val name: String?,
    val province: String,
    val representative: String,
    val phoneNumber: String
)
