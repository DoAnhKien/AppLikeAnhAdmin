package com.example.myapplication.base

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPref private constructor() {
    companion object {
        private val sharePref = SharedPref()
        private lateinit var sharedPreferences: SharedPreferences

        fun getInstance(context: Context): SharedPref {
            if (!Companion::sharedPreferences.isInitialized) {
                synchronized(SharedPref::class.java) {
                    if (!Companion::sharedPreferences.isInitialized) {
                        sharedPreferences =
                            context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String):String {
        return sharedPreferences.getString(key, "").toString()
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String):Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}