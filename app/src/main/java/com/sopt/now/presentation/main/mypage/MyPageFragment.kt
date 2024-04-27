package com.sopt.now.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sopt.now.MyApplication
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.presentation.auth.login.LoginActivity
import com.sopt.now.presentation.main.MainViewModel

class MyPageFragment : Fragment(){
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding)
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI() // 사용자 데이터 변경 시 UI 업데이트

        binding.btMypageLogoutButton.setOnClickListener{
            Log.d("logout" ,"$...")
            MyApplication.prefs.clearUserData()
            navigateToLogin()
        }
    }

    private fun updateUI() {
        viewModel.userInfo.observe(viewLifecycleOwner) { userData ->
            userData?.let {
                // UI 업데이트
                with(binding) {
                    tvMainIdContent.text = it.userid
                    tvMainPwContent.text = it.userpw
                    tvMainMbtiContent.text = it.usermbti
                    tvMainName.text = it.username
                }
            }
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