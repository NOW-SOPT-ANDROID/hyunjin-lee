package com.sopt.now.compose.ui.LoginScreen

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sopt.now.compose.data.auth.LoginData.LoginState
import com.sopt.now.compose.data.auth.LoginData.RequestLoginDto
import com.sopt.now.compose.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.compose.data.auth.ServicePool.authService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    // 로그인 상태를 담은 flow
    private val _loginstate = MutableSharedFlow<LoginState>()
    val loginstate = _loginstate.asSharedFlow()

    fun login(request: RequestLoginDto) {
        authService.login(request).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseLoginDto? = response.body()
                    val memberId = response.headers()["location"]
                    saveMemberIdToPreferences(memberId)

                    viewModelScope.launch {
                        _loginstate.emit(
                            LoginState(
                                isSuccess = true,
                                message = "로그인 성공! 유저 ID는 $memberId 입니다",
                                memberId = memberId
                            )
                        )
                    }
                } else {
                    // 오류 응답 처리
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseLoginDto::class.java)
                        viewModelScope.launch {
                            _loginstate.emit(
                                LoginState(
                                    isSuccess = false,
                                    message = "로그인 실패: ${errorResponse.message}" // 에러 메시지 사용
                                )
                            )
                        }
                    } catch (e: Exception) {
                        viewModelScope.launch {
                            _loginstate.emit(
                                LoginState(
                                    isSuccess = false,
                                    message = "로그인 실패: 에러 메시지 파싱 실패"
                                )
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                viewModelScope.launch {
                    _loginstate.emit(
                        LoginState(
                            isSuccess = false,
                            message = "서버에러: ${t.message}"
                        )
                    )
                }
            }
        })
    }

    // Shared Preferences에 memberId 저장하는 함수
    private fun saveMemberIdToPreferences(memberId: String?) {
        val sharedPref = getApplication<Application>().getSharedPreferences(
            "MyAppPreferences",
            Context.MODE_PRIVATE
        ) ?: return
        with(sharedPref.edit()) {
            putString("memberId", memberId)
            apply()
        }
    }
}