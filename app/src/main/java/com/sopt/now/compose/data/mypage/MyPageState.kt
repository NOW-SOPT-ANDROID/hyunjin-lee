package com.sopt.now.compose.data.mypage

data class MyPageState(
    val isSuccess: Boolean,
    val message: String,
    val userId: String? = null,
    val userName: String? = null,
    val userPhone: String? = null,
)