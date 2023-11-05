package com.example.warehousemanager.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.warehousemanager.database.models.Province

class UserPreference(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    //full name
    private val FULL_NAME = "full_name"

    //address
    private val ADDRESS = "address"
    val ID_ADDRESS = "id_address"

    //phone number
    private val PHONE_NUMBER = "phone_number"

    //link warehouse
    private val LINK_WAREHOUSE = "link_warehouse"

    //email
    private val EMAIL = "email"

    //password
    private val PASSWORD = "password"

    //warehouse
    private val WAREHOUSE = "warehouse"

    //warehouse keeper
    private val WAREHOUSE_KEEPER = "warehouse_keeper"

    //set full name
    fun setFullName(fullName: String) {
        preferences.edit().putString(FULL_NAME, fullName).apply()
    }

    //get full name
    fun getFullName(): String {
        return preferences.getString(FULL_NAME, "")!!
    }

    //set phone number
    fun setPhoneNumber(phoneNumber: String) {
        preferences.edit().putString(PHONE_NUMBER, phoneNumber).apply()
    }

    //get phone number
    fun getPhoneNumber(): String {
        return preferences.getString(PHONE_NUMBER, "")!!
    }

    fun setAddress(province: Province) {
        preferences.edit().putString(ADDRESS, province.name).apply()
        preferences.edit().putString(ID_ADDRESS, province.id).apply()
    }

    fun getAddress(): Province {
        if (
            preferences.getString(ID_ADDRESS, "")!!.isEmpty() || preferences.getString(
                ADDRESS, ""
            )!!.isEmpty()
        ) {
            return Province("", "")
        } else {
            return Province(
                preferences.getString(ID_ADDRESS, "")!!,
                preferences.getString(ADDRESS, "")!!
            )
        }

    }

    //set link warehouse
    fun setLinkWarehouse(linkWarehouse: String) {
        preferences.edit().putString(LINK_WAREHOUSE, linkWarehouse).apply()
    }

    //get link warehouse
    fun getLinkWarehouse(): String {
        return preferences.getString(LINK_WAREHOUSE, "")!!
    }

    //set email
    fun setEmail(email: String) {
        preferences.edit().putString(EMAIL, email).apply()
    }

    //get email
    fun getEmail(): String {
        return preferences.getString(EMAIL, "")!!
    }

    //set password
    fun setPassword(password: String) {
        preferences.edit().putString(PASSWORD, password).apply()
    }

    //get password
    fun getPassword(): String {
        return preferences.getString(PASSWORD, "")!!
    }

    //set warehouse
    fun setWarehouse(warehouse: String) {
        preferences.edit().putString(WAREHOUSE, warehouse).apply()
    }

    //get warehouse
    fun getWarehouse(): String {
        return preferences.getString(WAREHOUSE, "")!!
    }

    //set warehouse keeper
    fun setWarehouseKeeper(warehouseKeeper: String) {
        preferences.edit().putString(WAREHOUSE_KEEPER, warehouseKeeper).apply()
    }

    //get warehouse keeper
    fun getWarehouseKeeper(): String {
        return preferences.getString(WAREHOUSE_KEEPER, "")!!
    }

    //clear all preferences user and not clean all preferences
    fun clearPreferences() {
        preferences.edit().remove(FULL_NAME).apply()
        preferences.edit().remove(PHONE_NUMBER).apply()
        preferences.edit().remove(LINK_WAREHOUSE).apply()
        preferences.edit().remove(EMAIL).apply()
        preferences.edit().remove(PASSWORD).apply()
    }


}