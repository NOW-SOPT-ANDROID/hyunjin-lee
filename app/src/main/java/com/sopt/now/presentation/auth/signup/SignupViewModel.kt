package com.sopt.now.presentation.auth.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sopt.now.data.auth.SignUpData.RequestSignUpDto
import com.sopt.now.data.auth.SignUpData.ResponseSignUpDto
import com.sopt.now.data.auth.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<SignUpState>()

    // 회원가입 함수
    fun signUp(request: RequestSignUpDto) {
        authService.signUp(request).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    liveData.value = SignUpState(
                        isSuccess = true,
                        message = "회원가입 성공 유저의 ID는 $userId 입니둥",
                        userId = userId
                    )
                } else {
                    // 오류 응답 처리
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseSignUpDto::class.java)
                        liveData.value = SignUpState(
                            isSuccess = false,
                            message = "회원가입 실패: ${errorResponse.message}" // 에러 메시지 사용
                        )
                    } catch (e: Exception) {
                        liveData.value = SignUpState(
                            isSuccess = false,
                            message = "회원가입 실패: 에러 메시지 파싱 실패"
                        )
                    }
                }
            }
            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                liveData.value = SignUpState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}