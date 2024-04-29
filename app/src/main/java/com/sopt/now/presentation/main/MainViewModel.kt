package com.sopt.now.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sopt.now.data.User.UserDao
import com.sopt.now.data.User.UserData
import com.sopt.now.data.User.UserDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao: UserDao = UserDatabase.getUserDatabase(application).userDao()
    val userInfo: LiveData<UserData> = userDao.getAllUserInfo()

    fun loadUserInfo() {
        Log.d("user","${userDao.getAllUserInfo()}")
    }
}
