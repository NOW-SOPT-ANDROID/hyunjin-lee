package com.sopt.now.compose.ui.MypageScreen

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sopt.now.compose.data.auth.LoginData.LoginState
import com.sopt.now.compose.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.compose.data.auth.ServicePool
import com.sopt.now.compose.data.auth.User.ResponseUserDto
import com.sopt.now.compose.data.auth.User.UserState
import com.sopt.now.compose.data.mypage.MyPageState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel(application: Application) : AndroidViewModel(application) {
    // user 상태를 담은 flow
    private val _myInfo = MutableSharedFlow<MyPageState>()
    val myInfo = _myInfo.asSharedFlow()

    // user 정보 받아오기
    fun getUserInfo(memberIdString: String) {
        val memberId = memberIdString.toIntOrNull()?:0
        ServicePool.authService.getUserInfo(memberId).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserDto? = response.body()
                    val authenticationId = data?.data?.authenticationId
                    val nickname = data?.data?.nickname
                    val phone = data?.data?.phone

                    // memberId를 통해 회원 정보 받아옴
                    viewModelScope.launch {
                        _myInfo.emit(
                            MyPageState(
                                isSuccess = true,
                                message = "회원 조회 성공: ${data?.message}",
                                userId = authenticationId,
                                userName = nickname,
                                userPhone = phone
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
                            _myInfo.emit(
                                MyPageState(
                                    isSuccess = false,
                                    message = "회원 조회 실패: ${errorResponse.message}"
                                )
                            )
                        }
                    } catch (e: Exception) {
                        viewModelScope.launch {
                            _myInfo.emit(
                                MyPageState(
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
                    _myInfo.emit(
                        MyPageState(
                            isSuccess = false,
                            message = "서버 에러"
                        )
                    )
                }
            }
        })
    }
}