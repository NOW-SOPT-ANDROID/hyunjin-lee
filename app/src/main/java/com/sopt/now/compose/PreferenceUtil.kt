package com.sopt.now.compose

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sopt.now.compose.data.UserData

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getUserData(key: String): UserData {
        val userJson = prefs.getString(key, null)
        return if (userJson != null) {
            Gson().fromJson(userJson, UserData::class.java)
        } else {
            UserData("", "", "", "")
        }
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
}