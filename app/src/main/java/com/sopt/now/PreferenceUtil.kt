package com.sopt.now

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sopt.now.data.UserData

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getUserData(key: String): UserData {
        val userJson = prefs.getString(key, null)
        return Gson().fromJson(userJson, UserData::class.java) ?: UserData("", "", "", "", 0)
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun setBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun clearUserData() {
        prefs.edit().clear().apply()
    }

    companion object {
        const val PREFS_NAME = "prefs_name"
    }
}