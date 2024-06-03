package com.sopt.now.compose.data.auth

import com.sopt.now.compose.data.auth.LoginData.RequestLoginDto
import com.sopt.now.compose.data.auth.LoginData.ResponseLoginDto

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
}