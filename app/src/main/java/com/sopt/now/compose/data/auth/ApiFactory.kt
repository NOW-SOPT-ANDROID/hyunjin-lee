package com.sopt.now.compose.data.auth

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig
import com.sopt.now.compose.data.friend.FriendService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL : String = BuildConfig.AUTH_BASE_URL
    private const val FRIEND_URL: String = BuildConfig.AUTH_FRIEND_URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: FriendService by lazy {
        Retrofit.Builder()
            .baseUrl(FRIEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FriendService::class.java)
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val friendService = ApiFactory.api
}