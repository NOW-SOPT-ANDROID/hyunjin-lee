package com.sopt.now.compose.ui.SignupScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    val userId = MutableLiveData("")
    val userPw = MutableLiveData("")
    val userName = MutableLiveData("")
    val userMbti = MutableLiveData("")
}