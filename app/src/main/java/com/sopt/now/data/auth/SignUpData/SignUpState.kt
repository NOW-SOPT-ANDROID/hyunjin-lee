package com.sopt.now.data.auth.SignUpData

data class SignUpState(
    val isSuccess: Boolean,
    val message: String,
    val userId: String? = null,
)