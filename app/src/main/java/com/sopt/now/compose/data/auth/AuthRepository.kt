package com.sopt.now.compose.data.auth

import com.sopt.now.compose.data.auth.LoginData.RequestLoginDto
import com.sopt.now.compose.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.compose.data.auth.SignUpData.RequestSignUpDto
import com.sopt.now.compose.data.auth.SignUpData.ResponseSignUpDto

class AuthRepository(private val authService: AuthService) {
    suspend fun login(request: RequestLoginDto): Result<Pair<Int, ResponseLoginDto>> {
        return try {
            val response = authService.login(request)
            if (response.isSuccessful) {
                val memberId = response.headers()["location"]?.toIntOrNull()
                val responseBody = response.body()
                if (memberId != null && responseBody != null) {
                    Result.success(Pair(memberId, responseBody))
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(request: RequestSignUpDto): Result<Pair<Int, ResponseSignUpDto>> {
        return try {
            val response = authService.signUp(request)
            if (response.isSuccessful) {
                val memberId = response.headers()["location"]?.toIntOrNull()
                val data = response.body()
                if (memberId != null && data != null) {
                    Result.success(Pair(memberId, data))
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                val errorResponse = response.body()
                Result.failure(Exception(errorResponse?.message ?: "Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}