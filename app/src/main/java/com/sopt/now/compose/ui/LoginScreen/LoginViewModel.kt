package com.sopt.now.compose.ui.LoginScreen

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.auth.AuthRepository
import com.sopt.now.compose.data.auth.LoginData.LoginState
import com.sopt.now.compose.data.auth.LoginData.RequestLoginDto
import com.sopt.now.compose.data.auth.ServicePool
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepository by lazy { AuthRepository(ServicePool.authService) }
    private val _loginLiveData = MutableLiveData<LoginState>()
    val loginLiveData: LiveData<LoginState> = _loginLiveData

    fun login(request: RequestLoginDto) {
        viewModelScope.launch {
            authRepository.login(request)
                .onSuccess { (memberId, responseBody) ->
                    saveMemberIdToPreferences(memberId)
                    _loginLiveData.value = LoginState(
                        isSuccess = true,
                        message = "로그인 성공! 유저 ID는 $memberId 입니다",
                        memberId = memberId.toString()
                    )
                }
                .onFailure { exception ->
                    _loginLiveData.value = LoginState(
                        isSuccess = false,
                        message = "로그인 실패: ${exception.message}"
                    )
                }
        }
    }

    private fun saveMemberIdToPreferences(memberId: Int) {
        val sharedPref = getApplication<Application>().getSharedPreferences(
            "MyAppPreferences",
            Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putInt("memberId", memberId)
            apply()
        }
    }
}