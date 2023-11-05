package com.example.warehousemanager.database.dao

import android.content.ContentValues
import android.content.Context
import com.example.warehousemanager.database.WareHouseDBHelper
import com.example.warehousemanager.database.models.WarehouseKeeper

class WareHouseKeeperDAO(context: Context) {
    private val dbHelper: WareHouseDBHelper = WareHouseDBHelper(context)

    //insert warehouse keeper
    fun insertWarehouseKeeper(keeper: WarehouseKeeper) :Boolean{
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("id", keeper.id)
            put("password", keeper.password)
            put("name", keeper.name)
            put("email", keeper.email)
            put("phone_number", keeper.phoneNumber)
            put("birthday", keeper.birthday)
            put("warehouse_id", keeper.warehouseId)
        }
        val result: Long
        return try {
            result = db.insert("warehouse_keeper", null, values)
            result != -1L
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }

    //update warehouse keeper
    fun updateWarehouseKeeper(keeper: WarehouseKeeper) :Boolean{
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("password", keeper.password)
            put("name", keeper.name)
            put("email", keeper.email)
            put("phone_number", keeper.phoneNumber)
            put("birthday", keeper.birthday)
            put("warehouse_id", keeper.warehouseId)
        }
        val result: Int
        return try {
            result = db.update("warehouse_keeper", values, "id = ?", arrayOf(keeper.id))
            result != -1
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }

    //delete warehouse keeper
    fun deleteWarehouseKeeper(keeper: WarehouseKeeper) :Boolean{
        val db = dbHelper.writableDatabase
        val result: Int
        return try {
            result = db.delete("warehouse_keeper", "id = ?", arrayOf(keeper.id))
            result != -1
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }

    //check id warehouse keeper is exist
    fun isExistWarehouseKeeper(id: String): Boolean {
        val db = dbHelper.readableDatabase
        val query = "SELECT * FROM warehouse_keeper WHERE id = ?"
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