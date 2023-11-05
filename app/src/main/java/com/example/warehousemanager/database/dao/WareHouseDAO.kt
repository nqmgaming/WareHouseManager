package com.example.warehousemanager.database.dao

import android.content.ContentValues
import android.content.Context
import com.example.warehousemanager.database.WareHouseDBHelper
import com.example.warehousemanager.database.models.Warehouse

class WareHouseDAO(context: Context) {
    private val dbHelper: WareHouseDBHelper = WareHouseDBHelper(context)

    //insert warehouse
    fun insertWarehouse(warehouse: Warehouse): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("id", warehouse.id)
            put("name", warehouse.name)
            put("province_id", warehouse.province)
            put("representative", warehouse.representative)
            put("phone_number", warehouse.phoneNumber)
        }
        val result: Long
        return try {
            result = db.insert("warehouse", null, values)
            result != -1L
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }

    //update warehouse
    fun updateWarehouse(warehouse: Warehouse): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", warehouse.name)
            put("province_id", warehouse.province)
            put("representative", warehouse.representative)
            put("phone_number", warehouse.phoneNumber)
        }
        val result: Int
        return try {
            result = db.update("warehouse", values, "id = ?", arrayOf(warehouse.id))
            result != -1
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }

    //delete warehouse
    fun deleteWarehouse(warehouse: Warehouse): Boolean {
        val db = dbHelper.writableDatabase
        val result: Int
        return try {
            result = db.delete("warehouse", "id = ?", arrayOf(warehouse.id))
            result != -1
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }

    //check id warehouse is exist
    fun isWarehouseExist(id: String): Boolean {
        val db = dbHelper.readableDatabase
        val query = "SELECT * FROM warehouse WHERE id = ?"
        val cursor = db.rawQuery(query, arrayOf(id))
        return try {
            cursor.moveToFirst()
            cursor.count > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            cursor.close()
            db.close()
        }
    }

}