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

    fun clearUserData() {
        prefs.edit().clear().apply()
    }

    companion object {
        const val PREFS_NAME = "prefs_name"
        const val PREF_KEY = "user_data" // 사용자 데이터를 저장할 때 사용할 키 값
    }
}