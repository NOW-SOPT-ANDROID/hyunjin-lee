package com.sopt.now.compose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    val userId = MutableLiveData("")
    val userPw = MutableLiveData("")
    val userName = MutableLiveData("")
    val userMbti = MutableLiveData("")
}