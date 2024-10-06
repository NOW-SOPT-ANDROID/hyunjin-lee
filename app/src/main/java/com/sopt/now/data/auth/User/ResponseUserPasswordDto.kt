package com.sopt.now.data.auth.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserPasswordDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)