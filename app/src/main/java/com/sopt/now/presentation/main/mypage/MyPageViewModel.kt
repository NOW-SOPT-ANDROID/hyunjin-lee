package com.sopt.now.presentation.main.mypage

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sopt.now.data.MyPage.MyPageState
import com.sopt.now.data.auth.LoginData.LoginState
import com.sopt.now.data.auth.LoginData.RequestLoginDto
import com.sopt.now.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.data.auth.ServicePool
import com.sopt.now.data.auth.User.ResponseUserDto
import com.sopt.now.data.auth.User.UserState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel(application: Application) : AndroidViewModel(application) {
    private val authService by lazy { ServicePool.authService }
    private val _myInfo = MutableLiveData<MyPageState>()
    val myInfo: MutableLiveData<MyPageState> = _myInfo

    // user 정보 받아오기
    fun getUserInfo(memberId: Int) {
        authService.getUserInfo(memberId).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserDto? = response.body()
                    val authenticationId = data?.data?.authenticationId.toString()
                    val nickname = data?.data?.nickname.toString()
                    val phone = data?.data?.phone.toString()

                    // memberId를 통해 회원 정보 받아옴
                    _myInfo.value = MyPageState(
                        isSuccess = true,
                        message = "회원 조회 성공: ${data?.message}",
                        userId = authenticationId,
                        userName = nickname,
                        userPhone = phone
                    )
                } else {
                    // 오류 응답 처리
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseLoginDto::class.java)
                        _myInfo.value = MyPageState(
                            isSuccess = false,
                            message = "회원 조회 실패: ${errorResponse.message}"
                        )
                    } catch (e: Exception) {
                        _myInfo.value = MyPageState(
                            isSuccess = false,
                            message = "회원 조회 실패: 에러 메시지 파싱 실패"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                _myInfo.value = MyPageState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }
}