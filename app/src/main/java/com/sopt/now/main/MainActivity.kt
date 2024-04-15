package com.sopt.now.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.data.UserData
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.home.HomeFragment
import com.sopt.now.login.LoginActivity
import com.sopt.now.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 액티비티가 처음 생성될 때, HomeFragment를 화면에 표시
        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvHome.id, HomeFragment())
                .commit()
        }

        // 바텀 네비게이션 클릭 이벤트 처리 함수 호출
        setBottomNavigation()

        // LoginActivity에서 전달한 데이터 viewmodel에 저장
        intent.getParcelableExtra<UserData>(LoginActivity.USER_DATA)?.let{
            viewModel.setUserInfo(it)
        }
    }

    private fun setBottomNavigation() {
        // 바텀 네비게이션 아이템 선택 리스너 설정
        binding.bnvHome.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.menu_home-> { // home 선택
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_mypage-> { // my page 선택
                    replaceFragment(MyPageFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fcvHome.id, fragment)
            .commit()
    }
}




