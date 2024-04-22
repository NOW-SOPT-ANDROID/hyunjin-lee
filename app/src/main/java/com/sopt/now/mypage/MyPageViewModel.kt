package com.sopt.now.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.UserData

class MyPageViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<UserData>()
    val userInfo: LiveData<UserData> = _userInfo

    fun setUserInfo(user: UserData) {
        _userInfo.value = user
    }
}