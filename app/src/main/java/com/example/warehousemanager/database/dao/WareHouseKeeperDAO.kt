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
            put("avatar", keeper.avatar)
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
            put("avatar", keeper.avatar)
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

    //check password warehouse keeper is correct
    fun isCorrectPassword(id: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val query = "SELECT * FROM warehouse_keeper WHERE id = ? AND password = ?"
        val cursor = db.rawQuery(query, arrayOf(id, password))
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

    //get warehouse keeper by id and not include password
    fun getWarehouseKeeperById(id: String): WarehouseKeeper? {
        val db = dbHelper.readableDatabase
        val query = "SELECT * FROM warehouse_keeper WHERE id = ?"
        val cursor = db.rawQuery(query, arrayOf(id))
        return try {
            cursor.moveToFirst()
            val keeper = WarehouseKeeper(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7)
            )
            keeper.password = ""
            keeper
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            cursor.close()
            db.close()
        }
    }


    //set new password by id
    fun setNewPassword(id: String, password: String): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("password", password)
        }
        val result: Int
        return try {
            result = db.update("warehouse_keeper", values, "id = ?", arrayOf(id))
            result != -1
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }
}