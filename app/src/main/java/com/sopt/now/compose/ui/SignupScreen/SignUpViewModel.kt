package com.sopt.now.compose.ui.SignupScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.auth.AuthRepository
import com.sopt.now.compose.data.auth.ServicePool
import com.sopt.now.compose.data.auth.SignUpData.RequestSignUpDto
import com.sopt.now.compose.data.auth.SignUpData.SignUpState
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepository by lazy { AuthRepository(ServicePool.authService) }
    private val _signUpLiveData = MutableLiveData<SignUpState>()
    val signUpLiveData: LiveData<SignUpState> = _signUpLiveData

    fun signUp(request: RequestSignUpDto) {
        viewModelScope.launch {
            authRepository.signUp(request)
                .onSuccess { (memberId, responseBody) ->
                    _signUpLiveData.value = SignUpState(
                        isSuccess = true,
                        message = "회원가입 성공 유저의 ID는 $memberId 입니다.",
                        memberId = memberId.toString()
                    )
                }
                .onFailure { exception ->
                    _signUpLiveData.value = SignUpState(
                        isSuccess = false,
                        message = "회원가입에 실패했습니다. ${exception.message}"
                    )
                }
        }
    }
}