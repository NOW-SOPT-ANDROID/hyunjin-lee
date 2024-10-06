package com.sopt.now.presentation.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.auth.LoginData.LoginState
import com.sopt.now.data.auth.LoginData.RequestLoginDto
import com.sopt.now.data.ServicePool
import com.sopt.now.data.auth.User.UserState
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepository by lazy { AuthRepository(ServicePool.authService) }
    val login_liveData = MutableLiveData<LoginState>()
    val user_liveData = MutableLiveData<UserState>()

    fun getUserInfo(memberId: Int) {
        viewModelScope.launch {
            val result = authRepository.getUserInfo(memberId)
            result.onSuccess { response ->
                user_liveData.value = UserState(
                    isSuccess = true,
                    message = "회원 조회 성공: ${response.message}",
                    userId = response.data?.authenticationId
                )
            }.onFailure { exception ->
                user_liveData.value = UserState(
                    isSuccess = false,
                    message = "회원 조회 실패: ${exception.message}"
                )
            }
        }
    }

    fun login(request: RequestLoginDto) {
        viewModelScope.launch {
            authRepository.login(
                request
            ).onSuccess { (memberId, responseBody) ->
                login_liveData.postValue(
                    LoginState(
                        isSuccess = true,
                        message = responseBody.message,
                        memberId = memberId.toString(),
                    )
                )
            }.onFailure { exception ->
                login_liveData.postValue(
                    LoginState(
                        isSuccess = false,
                        message = "로그인 실패: ${exception.message}"
                    )
                )
            }
        }
    }
}