package com.sopt.now.presentation.auth.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.ServicePool
import com.sopt.now.data.auth.SignUpData.RequestSignUpDto
import com.sopt.now.data.auth.SignUpData.SignUpState
import com.sopt.now.presentation.auth.login.AuthRepository
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepository by lazy { AuthRepository(ServicePool.authService) }
    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _signUpState

    fun signUp(request: RequestSignUpDto) {
        viewModelScope.launch {
            authRepository.signUp(
                request
            ).onSuccess { (memberId, responseBody) ->
                _signUpState.postValue(
                    SignUpState(
                        isSuccess = true,
                        message = "회원가입 성공",
                        userId = memberId.toString()
                    )
                )
            }.onFailure { exception ->
                _signUpState.postValue(
                    SignUpState(
                        isSuccess = false,
                        message = "회원가입 실패: ${exception.message}"
                    )
                )
            }
        }
    }
}