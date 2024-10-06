package com.sopt.now.compose.data.auth.LoginData

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerializedName("authenticationId")
    val authenticationId: String,
    @SerializedName("password")
    val password: String
)