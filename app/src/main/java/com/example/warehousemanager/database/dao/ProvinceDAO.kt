package com.example.warehousemanager.database.dao

import android.content.Context
import com.example.warehousemanager.database.WareHouseDBHelper
import com.example.warehousemanager.database.models.Province

class ProvinceDAO(context: Context) {
    private val db: WareHouseDBHelper = WareHouseDBHelper(context)

    //get all provinces
    fun getAllProvinces(): ArrayList<Province> {
        val provincesList = ArrayList<Province>()
        val db = db.readableDatabase
        val query = "SELECT * FROM provinces"
        val cursor = db.rawQuery(query, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val province = Province(
                    cursor.getString(0),
                    cursor.getString(1)
                )
                provincesList.add(province)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return provincesList

    }
}