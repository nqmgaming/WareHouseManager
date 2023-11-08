package com.example.warehousemanager.database.models

data class WarehouseKeeper(
    val id: String,
    var password: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val birthday: String?,
    val warehouseId: String,
    val avatar: String?
)
