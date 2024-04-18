package com.sopt.now.mypage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sopt.now.data.UserData
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.login.LoginActivity
import com.sopt.now.main.MainViewModel

class MyPageFragment : Fragment(){
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val USER_DATA = "user_data"
        const val USER_ID = "user_id"
        const val USER_PW = "user_pw"
        const val USER_NAME = "user_name"
        const val USER_MBTI = "user_mbti"
        const val IS_AUTO_LOGIN = "is_auto_login"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        sharedPreferences = activity?.getSharedPreferences("is_auto_login", Context.MODE_PRIVATE) ?: return binding.root

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userInfo.observe(viewLifecycleOwner) { user ->
            updateUI(user) // 사용자 데이터 변경 시 UI 업데이트
        }
        binding.btMypageLogoutButton.setOnClickListener{
            logout()
        }
    }

    private fun logout() {
        // 사용자 정보 삭제
        sharedPreferences.edit().apply {
            clear() // 모든 데이터 삭제
            putBoolean(IS_AUTO_LOGIN, false) // 자동 로그인 비활성화
            apply() // 변경 사항 적용
        }

        // 로그인 화면으로 이동하면서 액티비티 스택 정리
        navigateToLogin()
    }

    private fun updateUI(user: UserData) {
        with(binding) {
            tvMainIdContent.text = user.id
            tvMainPwContent.text = user.pw
            tvMainMbtiContent.text = user.mbti
            tvMainName.text = user.name
        }
    }

    // 로그인 화면으로 이동하는 메서드
    private fun navigateToLogin() {
        val intent = Intent(activity, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        activity?.finish() // 현재 액티비티 종료
    }

    override fun onDestroyView() {
        _binding = null // 메모리 누수 방지를 위해 뷰 바인딩 참조 해제
        super.onDestroyView()
    }
}