package com.sopt.now.presentation.auth.signup

data class SignUpState(
    val isSuccess: Boolean,
    val message: String,
    val userId: String? = null
)