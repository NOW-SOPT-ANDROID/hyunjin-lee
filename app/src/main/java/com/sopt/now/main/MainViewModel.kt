package com.sopt.now.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sopt.now.data.UserDao
import com.sopt.now.data.UserData
import com.sopt.now.data.UserDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao: UserDao = UserDatabase.getUserDatabase(application).userDao()
    val userInfo: LiveData<UserData> = userDao.getAllUserInfo()

    fun loadUserInfo() {
        Log.d("user","${userDao.getAllUserInfo()}")
    }
}
