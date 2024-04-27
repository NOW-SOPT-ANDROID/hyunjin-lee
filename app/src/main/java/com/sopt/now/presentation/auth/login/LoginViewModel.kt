package com.sopt.now.presentation.auth.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.User.UserDatabase
import com.sopt.now.data.User.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = UserDatabase.getUserDatabase(application).userDao()
    private val userRepository: UserRepository = UserRepository(userDao)
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(id: String, pw: String) {
        viewModelScope.launch {
            try {
                val userInfo = userRepository.getUserInfo(id)
                if (userInfo != null && userInfo.userpw == pw) {
                    _loginResult.postValue(true)
                } else {
                    _loginResult.postValue(false)
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "로그인 실패 ㅠㅠ..", e)
                _loginResult.postValue(false)
            }
        }
    }
}