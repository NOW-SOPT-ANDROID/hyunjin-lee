package com.sopt.now.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.MyApplication
import com.sopt.now.data.UserData

class LoginViewModel : ViewModel(){
    // 로그인 정보를 담은 User를 LiveData로 관리
    private val _userInfo: MutableLiveData<Boolean> = MutableLiveData(false)
    val userInfo: LiveData<Boolean> = _userInfo

    init {
        setUserInfo()
    }

    private fun setUserInfo() {
        if (MyApplication.prefs.getBoolean(LOGIN_STATE_KEY))
            _userInfo.value = true
    }

    fun getUserInfo(): UserData {
        return MyApplication.prefs.getUserData(PREF_KEY)
    }

    fun loginValid(id: String, pw: String, userData: UserData): Boolean {
        when {
            id.isBlank() -> return false
            pw.isBlank() -> return false
            id == userData.id && pw == userData.pw -> {
                MyApplication.prefs.setBoolean(LOGIN_STATE_KEY, true)
                setUserInfo()
                return true
            }
            else -> return false
        }
    }

    companion object {
        private const val PREF_KEY = "userData"
        private const val LOGIN_STATE_KEY = "true"
    }
}