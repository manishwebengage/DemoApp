package com.example.demoapp

import android.content.Context
import android.content.SharedPreferences

object SharedPref{
    val FILE_NAME = "PUSH_SHARED_PREFS"
    val CUID_KEY = "cuid"

    fun writeCuidToSharedPreferences(context: Context, cuid: String) {
        getSharedPrefs(context).edit().putString(CUID_KEY, cuid).apply()
    }

    fun clearSharedPreferences(context: Context) {
        getSharedPrefs(context).edit().remove(CUID_KEY).apply()
    }

    fun getCuidFromSharedPrefs(context: Context): String? {
        return getSharedPrefs(context).getString(CUID_KEY, null)
    }

    fun getSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }
}