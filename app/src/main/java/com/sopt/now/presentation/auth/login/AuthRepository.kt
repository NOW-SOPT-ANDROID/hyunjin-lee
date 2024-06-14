package com.sopt.now.presentation.auth.login

import com.google.gson.Gson
import com.sopt.now.data.auth.AuthService
import com.sopt.now.data.auth.LoginData.RequestLoginDto
import com.sopt.now.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.data.auth.SignUpData.RequestSignUpDto
import com.sopt.now.data.auth.SignUpData.ResponseSignUpDto
import com.sopt.now.data.auth.User.ResponseUserDto

class AuthRepository(private val authService: AuthService) {
    suspend fun getUserInfo(memberId: Int): Result<ResponseUserDto> {
        return runCatching {
            val response = authService.getUserInfo(memberId).execute()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ResponseUserDto::class.java)
                throw Exception(errorResponse.message)
            }
        }
    }

    suspend fun login(request: RequestLoginDto): Result<Pair<Int, ResponseLoginDto>> {
        return runCatching {
            val response = authService.login(request)
            if (response.isSuccessful) {
                val memberId = response.headers()["location"]?.toIntOrNull()
                val responseBody = response.body()
                if (memberId != null && responseBody != null) {
                    Pair(memberId, responseBody)
                } else {
                    throw Exception("Invalid response")
                }
            } else {
                throw Exception(response.message())
            }
        }
    }

    suspend fun signUp(request: RequestSignUpDto): Result<Pair<Int, ResponseSignUpDto>> {
        return runCatching {
            val response = authService.signUp(request)
            if (response.isSuccessful) {
                val memberId = response.headers()["location"]?.toIntOrNull()
                val responseBody = response.body()
                if (memberId != null && responseBody != null) {
                    Pair(memberId, responseBody)
                } else {
                    throw Exception("Invalid response")
                }
            } else {
                throw Exception(response.message())
            }
        }
    }
}