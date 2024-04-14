package com.sopt.now.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.UserData

class LoginViewModel : ViewModel(){
    // 로그인 정보를 담은 User를 LiveData로 관리
    private val _userInfo = MutableLiveData<UserData>()
    val userInfo: LiveData<UserData> = _userInfo

    fun setUserInfo(user: UserData) {
        _userInfo.value = user
    }

    fun loginValid(id: String, pw: String): Boolean {
        return userInfo.value?.let {
            it.id == id && it.pw == pw
        } ?: false
    }
}