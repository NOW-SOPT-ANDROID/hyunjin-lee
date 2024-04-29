package com.sopt.now.compose.ui.MypageScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.MyApplication
import com.sopt.now.compose.data.UserData

class MyPageViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<UserData>()

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