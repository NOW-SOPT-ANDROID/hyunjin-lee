package com.sopt.now.compose.data.auth

import com.sopt.now.compose.data.auth.LoginData.RequestLoginDto
import com.sopt.now.compose.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.compose.data.auth.SignUpData.RequestSignUpDto
import com.sopt.now.compose.data.auth.SignUpData.ResponseSignUpDto
import com.sopt.now.compose.data.auth.User.ResponseUserDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): Response<ResponseSignUpDto>

    @POST("member/login")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<ResponseLoginDto>

    @GET("member/info")
    fun getUserInfo(
        @Header("memberId") memberId: Int, // Header에 memberId 전달
    ): Call<ResponseUserDto>
}