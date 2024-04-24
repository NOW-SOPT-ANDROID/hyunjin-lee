package com.sopt.now.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.AppDatabase
import com.sopt.now.data.Friend
import com.sopt.now.data.FriendDao
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val friendDao: FriendDao = AppDatabase.getDatabase(application).friendDao()
    private val _mockFriendList = MutableLiveData<List<Friend>>()
    val mockFriendList: LiveData<List<Friend>> = _mockFriendList

    init {
        loadFriends()
    }

    // 친구 list load
    private fun loadFriends() {
        viewModelScope.launch {
            _mockFriendList.value = friendDao.getAllFriendsList()
        }
    }

     // 친구 추가
    fun addFriend(newFriend: Friend) {
        Log.d("add", "${newFriend}")
        viewModelScope.launch {
            friendDao.insertFriend(newFriend)
            loadFriends()
        }
    }

    // 친구 삭제
    fun removeFriend(friend: Friend) {
        viewModelScope.launch {
            friendDao.deleteFriend(friend)
            loadFriends()
        }
    }
}