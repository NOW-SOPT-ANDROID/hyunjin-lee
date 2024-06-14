package com.sopt.now.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.presentation.main.home.HomeFragment
import com.sopt.now.presentation.main.mypage.MyPageFragment
import com.sopt.now.presentation.main.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var backPressedTime: Long = 0
    private var lastSelectedItemId = R.id.menu_home // 초기 선택된 탭 지정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // userId를 Intent에서 가져옴
        val memberId = intent.getStringExtra(MEMBER_ID)

        replaceFragment(HomeFragment())
        // 바텀 네비게이션 클릭 이벤트 처리 함수 호출
        setBottomNavigation(memberId)
    }

    // 뒤로가기 두 번 누르면 종료
    override fun onBackPressed() {
        // 현재 액티비티와 같은 태스크에 있는 모든 액티비티를 종료 -> 앱 종료
        if (backPressedTime + BACK_PRESSED_DURATION > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            Toast.makeText(this, R.string.back_pressed, Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun setBottomNavigation(memberId: String?) {
        // 바텀 네비게이션 아이템 선택 리스너 설정
        binding.bnvHome.setOnItemSelectedListener { it ->
            if (it.itemId == lastSelectedItemId) {
                // 같은 탭을 다시 선택한 경우
                scrollToTop()
            } else {
                // 다른 탭을 선택한 경우
                when (it.itemId) {
                    R.id.menu_home -> { // home 선택
                        replaceFragment(HomeFragment())
                    }

                    R.id.menu_search -> { // search 선택
                        replaceFragment(SearchFragment())
                    }

                    R.id.menu_mypage -> { // my page 선택
                        val myPageFragment = MyPageFragment().apply {
                            arguments = Bundle().apply {
                                putString(MEMBER_ID, memberId) // userId를 MyPageFragment에 전달
                            }
                        }
                        replaceFragment(myPageFragment)
                    }

                    else -> false
                }
                lastSelectedItemId = it.itemId
            }
            true
        }
    }

    private fun scrollToTop() {
        val fragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (fragment is HomeFragment) {
            fragment.scrollToTop()
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
        private const val MEMBER_ID = "memberId"
    }
}