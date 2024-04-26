package com.sopt.now.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.UserData
import com.sopt.now.data.UserDatabase
import com.sopt.now.main.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = UserDatabase.getUserDatabase(application).userDao()
    private val userRepository: UserRepository
    private val _userInfo = MutableLiveData<UserData?>()
    val userInfo: LiveData<UserData?> = _userInfo
    val allUserInfo: LiveData<List<UserData>> = userDao.getAllUserListInfo()

    fun logAllUserInfo() {
        allUserInfo.observeForever { userList ->
            userList.forEach { userData ->
                Log.d("AllUserInfo", "ID: ${userData.userid}, PW: ${userData.userpw}, Name: ${userData.username}, MBTI: ${userData.usermbti}, Image ID: ${userData.userprofileImage}")
            }
        }
    }
    init {
        userRepository = UserRepository(userDao)
    }

    fun getUserInfo(id: String) {
        viewModelScope.launch {
            try {
                val userInfo = userRepository.getUserInfo(id)
                _userInfo.postValue(userInfo) // 백그라운드 스레드에서 안전하게 LiveData 업데이트
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Failed to fetch user info", e)
            }
        }
    }
}