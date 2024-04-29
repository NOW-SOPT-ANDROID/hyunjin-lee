package com.sopt.now.presentation.auth

data class UserState(
    val isSuccess: Boolean,
    val message: String,
    val memberId: String? = null
)