package com.sopt.now.presentation.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sopt.now.data.auth.LoginData.LoginState
import com.sopt.now.data.auth.LoginData.RequestLoginDto
import com.sopt.now.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.data.ServicePool
import com.sopt.now.data.auth.User.ResponseUserDto
import com.sopt.now.data.auth.User.UserState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val authService by lazy { ServicePool.authService }
    val login_liveData = MutableLiveData<LoginState>()
    val user_liveData = MutableLiveData<UserState>()

    // 회원가입 정보 받아오기
    fun getUserInfo(memberId: Int) {
        authService.getUserInfo(memberId).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserDto? = response.body()
                    val authenticationId = data?.data?.authenticationId

                    // memberId를 통해 회원 정보 받아옴
                    user_liveData.value = UserState(
                        isSuccess = true,
                        message = "회원 조회 성공: ${data?.message}",
                        userId = authenticationId
                    )
                } else {
                    // 오류 응답 처리
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseUserDto::class.java)
                        user_liveData.value = UserState(
                            isSuccess = false,
                            message = "회원 조회 실패: ${errorResponse.message}"
                        )
                    } catch (e: Exception) {
                        user_liveData.value = UserState(
                            isSuccess = false,
                            message = "회원 조회 실패: 에러 메시지 파싱 실패"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                login_liveData.value = LoginState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }

    fun login(request: RequestLoginDto) {
        authService.login(request).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseLoginDto? = response.body()
                    val memberId = response.headers()["location"]
                    login_liveData.value = LoginState(
                        isSuccess = true,
                        message = "로그인 성공 유저의 ID는 $memberId 입니둥",
                        memberId = memberId
                    )
                } else {
                    // 오류 응답 처리
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseLoginDto::class.java)
                        login_liveData.value = LoginState(
                            isSuccess = false,
                            message = "로그인 실패: ${errorResponse.message}" // 에러 메시지 사용
                        )
                    } catch (e: Exception) {
                        login_liveData.value = LoginState(
                            isSuccess = false,
                            message = "로그인 실패: 에러 메시지 파싱 실패"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                login_liveData.value = LoginState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}