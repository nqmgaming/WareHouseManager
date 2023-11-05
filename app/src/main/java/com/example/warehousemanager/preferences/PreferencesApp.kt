package com.example.warehousemanager.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.warehousemanager.database.models.Province

class PreferencesApp(private val context: Context) {
    private val KEY_FIRST_TIME: String = "first_time"
    private val IS_LOGGED_IN: String = "is_logged_in"
    private val EMAIL = "email"
    private val preferences: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setFirstTime(firstTime: Boolean) {
        preferences.edit().putBoolean(KEY_FIRST_TIME, firstTime).apply()
    }

    fun getFirstTime(): Boolean {
        return preferences.getBoolean(KEY_FIRST_TIME, true)
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        preferences.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun getIsLoggedIn(): Boolean {
        return preferences.getBoolean(IS_LOGGED_IN, false)
    }

}