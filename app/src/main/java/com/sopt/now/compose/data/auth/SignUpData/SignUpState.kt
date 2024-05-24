package com.sopt.now.compose.data.auth.SignUpData

data class SignUpState(
    val isSuccess: Boolean,
    val message: String,
    val memberId: String? = null,
)