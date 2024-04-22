package com.sopt.now.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.MyApplication
import com.sopt.now.data.UserData

class MainViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<UserData>()
    val userInfo: LiveData<UserData> = _userInfo

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        val userData = MyApplication.prefs.getUserData(PREF_KEY)
        _userInfo.value = userData
    }

    fun getUserInfo(): UserData {
        return MyApplication.prefs.getUserData(PREF_KEY)
    }

    companion object {
        private const val PREF_KEY = "userData"
    }
}