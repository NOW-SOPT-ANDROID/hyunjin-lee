package com.sopt.now.compose.ui.SignupScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    var userId = mutableStateOf("")
    var userPw = mutableStateOf("")
    var userName = mutableStateOf("")
    var userMbti = mutableStateOf("")
}