package com.sopt.now.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.MyApplication
import com.sopt.now.data.UserData

class MainViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<UserData>()
    val userInfo: LiveData<UserData> = _userInfo

    fun setUserInfo(user: UserData) {
        _userInfo.value = user
    }

    fun getUserInfo(): UserData {
        return MyApplication.prefs.getUserData(PREF_KEY)
    }

    companion object {
        private const val PREF_KEY = "userData"
    }
}