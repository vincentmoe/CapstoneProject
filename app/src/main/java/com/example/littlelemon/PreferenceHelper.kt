package com.example.littlelemon

import android.content.Context

class PreferencesHelper(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getData(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }
}