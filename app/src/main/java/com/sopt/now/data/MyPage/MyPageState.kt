package com.sopt.now.data.MyPage

data class MyPageState(
    val isSuccess: Boolean,
    val message: String,
    val userId: String? = null,
    val userName: String? = null,
    val userPhone: String? = null,
)