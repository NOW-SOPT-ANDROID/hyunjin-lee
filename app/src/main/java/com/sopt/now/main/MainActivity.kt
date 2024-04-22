package com.sopt.now.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.home.HomeFragment
import com.sopt.now.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replaceFragment(HomeFragment())
        // 바텀 네비게이션 클릭 이벤트 처리 함수 호출
        setBottomNavigation()
    }

    // 뒤로가기 두 번 누르면 종료
    override fun onBackPressed() {
        // 현재 액티비티와 같은 태스크에 있는 모든 액티비티를 종료 -> 앱 종료
        if (backPressedTime + BACK_PRESSED_DURATION > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            Toast.makeText(this, "뒤로가기를 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
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

    companion object {
        // 앱이 종료되는 최대 시간
        const val BACK_PRESSED_DURATION = 2000L
    }
}