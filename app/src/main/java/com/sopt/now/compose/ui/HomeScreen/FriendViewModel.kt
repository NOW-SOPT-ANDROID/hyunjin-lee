package com.sopt.now.compose.ui.HomeScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.auth.ServicePool
import com.sopt.now.compose.data.friend.Friend_
import com.sopt.now.compose.data.friend.ResponseFriendDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendViewModel : ViewModel() {
    private val friendService = ServicePool.friendService
    val users: MutableLiveData<List<Friend_>> = MutableLiveData()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        friendService.getUsers(2).enqueue(object : Callback<ResponseFriendDto> {
            override fun onResponse(
                call: Call<ResponseFriendDto>,
                response: Response<ResponseFriendDto>,
            ) {
                if (response.isSuccessful) {
                    // 성공적으로 데이터를 받아온 경우
                    users.postValue(response.body()?.data ?: emptyList())
                } else {
                    // 요청이 실패한 경우
                    users.postValue(emptyList())
                    Log.d("friend", "실패...")
                }
            }

            override fun onFailure(call: Call<ResponseFriendDto>, t: Throwable) {
                // 네트워크 요청 자체가 실패한 경우
                users.postValue(emptyList())
                Log.d("friend", "네트워크 실패...")
            }
        })
    }
}