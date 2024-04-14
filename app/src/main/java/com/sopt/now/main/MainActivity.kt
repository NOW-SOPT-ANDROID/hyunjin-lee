package com.sopt.now.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.data.UserData
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUserData()
        setUserData()

        setContentView(binding.root)
    }

    private fun getUserData() {
        val user: UserData? = intent.getParcelableExtra(USER_DATA) // User 객체 받기
        if (user != null) {
            viewModel.setUserInfo(user) // ViewModel에 User 객체 적용
        }
    }

    private fun setUserData() {
        val user = viewModel.userInfo.value ?: return
        with(binding) {
            tvMainIdContent.text = user.id
            tvMainPwContent.text = user.pw
            tvMainMbtiContent.text = user.mbti
            tvMainName.text = user.name
        }
    }

    // 상수화
    companion object {
        const val USER_DATA = "user_data"
    }
}




