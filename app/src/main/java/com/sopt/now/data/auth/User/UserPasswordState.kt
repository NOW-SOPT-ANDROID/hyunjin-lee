package com.sopt.now.data.auth.User

data class UserPasswordState(
    val isSuccess: Boolean,
    val message: String,
    val previousPassword: String? = null,
    val newPassword: String? = null,
    val newPasswordVerification: String? = null,
)