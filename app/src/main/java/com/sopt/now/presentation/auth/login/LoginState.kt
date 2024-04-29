package com.sopt.now.presentation.auth.login

data class LoginState(
    val isSuccess: Boolean,
    val message: String,
    val memberId: String? = null
)