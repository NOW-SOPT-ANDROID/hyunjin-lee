package com.sopt.now.data.auth.User

data class UserState(
    val isSuccess: Boolean,
    val message: String,
    val userId: String? = null,
)