package com.sopt.now.compose.data.auth.LoginData

data class LoginState(
    val isSuccess: Boolean,
    val message: String,
    val memberId: String? = null
)