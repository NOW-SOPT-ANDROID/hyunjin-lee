package com.sopt.now.data.auth.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserData_,
)

@Serializable
data class UserData_(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("phone")
    val phone: String,
)