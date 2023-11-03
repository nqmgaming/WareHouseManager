package com.example.warehousemanager.database.models

data class WarehouseStaff(
    val id: String,
    val password: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val birthday: String, // You might want to use a proper date type here
    val warehouseId: String, // Reference to the corresponding warehouse
    val keeperId: String // Reference to the corresponding warehouse keeper
)

