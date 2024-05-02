package com.sopt.now.compose.ui.SignupScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sopt.now.compose.data.auth.ServicePool
import com.sopt.now.compose.data.auth.SignUpData.RequestSignUpDto
import com.sopt.now.compose.data.auth.SignUpData.ResponseSignUpDto
import com.sopt.now.compose.data.auth.SignUpData.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel() : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    private var isLoading = MutableStateFlow(false)
    private val _signUpState = MutableStateFlow(SignUpState(isSuccess = false, ""))
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun signUp(request: RequestSignUpDto) {
        authService.signUp(request).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val memberId = response.headers()["location"]

                    viewModelScope.launch {
                        _signUpState.emit(
                            SignUpState(
                                isSuccess = true,
                                message = "회원가입 성공 유저의 ID는 $memberId 입니둥",
                                memberId = memberId
                            )
                        )
                    }
                } else {
                    // 오류 응답 처리
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseSignUpDto::class.java)
                        viewModelScope.launch {
                            _signUpState.emit(
                                SignUpState(
                                    isSuccess = false,
                                    message = "회원가입 실패: ${errorResponse.message}" // 에러 메시지 사용
                                )
                            )
                        }
                    } catch (e: Exception) {
                        viewModelScope.launch {
                            _signUpState.emit(
                                SignUpState(
                                    isSuccess = false,
                                    message = "회원가입 실패: 에러 메시지 파싱 실패"
                                )
                            )
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                viewModelScope.launch {
                    isLoading.emit(false) // 요청 실패 시 isLoading 상태 변경
                    _signUpState.emit(
                        SignUpState(
                            isSuccess = false,
                            message = "서버에러"
                        )
                    )
                }
            }
        })
    }
}