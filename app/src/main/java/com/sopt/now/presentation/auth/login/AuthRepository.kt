package com.sopt.now.presentation.auth.login

import com.google.gson.Gson
import com.sopt.now.data.auth.AuthService
import com.sopt.now.data.auth.LoginData.RequestLoginDto
import com.sopt.now.data.auth.LoginData.ResponseLoginDto
import com.sopt.now.data.auth.User.ResponseUserDto
import retrofit2.Response

class AuthRepository(private val authService: AuthService) {
    suspend fun getUserInfo(memberId: Int): Result<ResponseUserDto> {
        return try {
            val response = authService.getUserInfo(memberId).execute()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ResponseUserDto::class.java)
                Result.failure(Exception(errorResponse.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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