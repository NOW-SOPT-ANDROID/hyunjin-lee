package com.sopt.now.data.Friend

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendService {
    @GET("api/users")
    fun getUsers(
        @Query("page") page: Int,
    ): Call<ResponseFriendDto>
}