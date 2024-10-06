package com.sopt.now.compose

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sopt.now.compose.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.compose.data.auth.ServicePool.authService
import com.sopt.now.compose.data.auth.User.ResponseUserDto
import com.sopt.now.compose.data.auth.User.UserState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _userInfo = MutableSharedFlow<UserState>()
    val userInfo = _userInfo.asSharedFlow()

    // 회원가입 정보 받아오기
    fun getUserInfo(memberIdString: String) {
        val memberId = memberIdString.toIntOrNull()?:0
        Log.d("mainvew", "$memberId")
        authService.getUserInfo(memberId).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserDto? = response.body()
                    val authenticationId = data?.data?.authenticationId

                    // memberId를 통해 회원 정보 받아옴
                    viewModelScope.launch {
                        _userInfo.emit(
                            UserState(
                                isSuccess = true,
                                message = "회원 조회 성공: ${data?.message}",
                                userId = authenticationId.toString()
                            )
                        )
                    }
                    Log.d("mainvew", authenticationId.toString())
                } else {
                    // 오류 응답 처리
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseLoginDto::class.java)
                        viewModelScope.launch {
                            _userInfo.emit(
                                UserState(
                                    isSuccess = false,
                                    message = "회원 조회 실패: ${errorResponse.message}"
                                )
                            )
                        }
                    } catch (e: Exception) {
                        viewModelScope.launch {
                            _userInfo.emit(
                                UserState(
                                    isSuccess = false,
                                    message = "회원 조회 실패: 에러 메시지 파싱 실패"
                                )
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                viewModelScope.launch {
                    _userInfo.emit(
                        UserState(
                            isSuccess = false,
                            message = "서버 에러"
                        )
                    )
                }
            }
        })
    }
}