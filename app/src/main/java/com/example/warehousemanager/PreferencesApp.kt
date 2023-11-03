package com.example.warehousemanager

import android.content.Context
import android.content.SharedPreferences

class PreferencesApp(private val context: Context) {
    private val KEY_FIRST_TIME: String = "first_time"
    private val IS_LOGGED_IN: String = "is_logged_in"
    private val preferences: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setFirstTime(firstTime: Boolean) {
        preferences.edit().putBoolean(KEY_FIRST_TIME, firstTime).apply()
    }

    fun getFirstTime(): Boolean {
        return preferences.getBoolean(KEY_FIRST_TIME, true)
    }

    fun clearPreferences() {
        preferences.edit().clear().apply()
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        preferences.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun getIsLoggedIn(): Boolean {
        return preferences.getBoolean(IS_LOGGED_IN, false)
    }


}