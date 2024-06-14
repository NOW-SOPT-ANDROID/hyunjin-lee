package com.sopt.now.compose.data.auth

import com.sopt.now.compose.data.auth.LoginData.RequestLoginDto
import com.sopt.now.compose.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.compose.data.auth.SignUpData.RequestSignUpDto
import com.sopt.now.compose.data.auth.SignUpData.ResponseSignUpDto

class AuthRepository(private val authService: AuthService) {
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
                val data = response.body()
                if (memberId != null && data != null) {
                    Pair(memberId, data)
                } else {
                    throw Exception("Invalid response")
                }
            } else {
                val errorResponse = response.body()
                throw Exception(errorResponse?.message ?: "Unknown error")
            }
        }
    }
}