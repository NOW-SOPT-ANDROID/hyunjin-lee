package com.sopt.now.presentation.main.mypage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sopt.now.R
import com.sopt.now.data.MyPage.MyPageState
import com.sopt.now.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.data.ServicePool
import com.sopt.now.data.auth.User.RequestUserPasswordDto
import com.sopt.now.data.auth.User.ResponseUserDto
import com.sopt.now.data.auth.User.ResponseUserPasswordDto
import com.sopt.now.data.auth.User.UserPasswordState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel(application: Application) : AndroidViewModel(application) {
    private val authService by lazy { ServicePool.authService }
    private val _myInfo = MutableLiveData<MyPageState>()
    val myInfo: MutableLiveData<MyPageState> = _myInfo

    private val _newpassword = MutableLiveData<UserPasswordState>()
    val newpassword: MutableLiveData<UserPasswordState> = _newpassword

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
                        memberId = memberId,
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

    fun patchUserPassword(
        memberId: String,
        previous: String,
        new: String,
        newVerification: String,
    ) {
        // 일단 이전 비밀번호, 새 비밀번호, 새 비밀번호 확인이 유효한지 체크
        if (new != newVerification) {
            _newpassword.value = UserPasswordState(
                isSuccess = false,
                message = "새 비밀번호와 확인이 일치하지 않습니다."
            )
            return
        }

        // Request Body 생성
        val requestBody = RequestUserPasswordDto(
            previousPassword = previous,
            newPassword = new,
            newPasswordVerification = newVerification
        )

        // API 호출을 통해 서버에 비밀번호 변경 요청
        authService.patchUserPassword(memberId.toInt(), requestBody)
            .enqueue(object : Callback<ResponseUserPasswordDto> {
                override fun onResponse(
                    call: Call<ResponseUserPasswordDto>,
                    response: Response<ResponseUserPasswordDto>,
                ) {
                    if (response.isSuccessful) {
                        // 비밀번호 변경 성공 처리
                        val data: ResponseUserPasswordDto? = response.body()
                        _newpassword.value = UserPasswordState(
                            isSuccess = true,
                            message = "${R.string.change_pw_success}"
                        )
                    } else {
                        // 오류 응답 처리
                        val error = response.errorBody()?.string()
                        val gson = Gson()
                        try {
                            val errorResponse =
                                gson.fromJson(error, ResponseUserPasswordDto::class.java)
                            _newpassword.value = UserPasswordState(
                                isSuccess = false,
                                message = "${R.string.fail_data} + ${errorResponse.message}"
                            )
                        } catch (e: Exception) {
                            _newpassword.value = UserPasswordState(
                                isSuccess = false,
                                message = "${R.string.fail_data} + : 에러 메시지 파싱 실패"
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseUserPasswordDto>, t: Throwable) {
                    // 네트워크 오류 등 서버 에러 처리
                    _newpassword.value = UserPasswordState(
                        isSuccess = false,
                        message = "${R.string.fail_network}: ${t.message}"
                    )
                }
            })
    }
}