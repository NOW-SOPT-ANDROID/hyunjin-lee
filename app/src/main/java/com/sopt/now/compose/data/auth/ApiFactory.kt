package com.sopt.now.compose.data.auth

import com.sopt.now.BuildConfig
import com.sopt.now.compose.data.friend.FriendService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL
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