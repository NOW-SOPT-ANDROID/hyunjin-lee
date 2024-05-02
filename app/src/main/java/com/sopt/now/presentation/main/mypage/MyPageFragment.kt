package com.sopt.now.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.sopt.now.MyApplication
import com.sopt.now.data.MyPage.MyPageState
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.presentation.auth.login.LoginActivity
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.presentation.main.MainViewModel
import kotlinx.coroutines.launch

class MyPageFragment : Fragment(){
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding)
    private val my_viewModel: MyPageViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUserData()
        initObserver()
    }

    private fun initViews() {
//        // logout 버튼 기능
//        binding.btMypageLogoutButton.setOnClickListener{
//            Log.d("logout" ,"$...")
//            MyApplication.prefs.clearUserData()
//            navigateToLogin()
//        }
    }

    private fun initObserver() {
        my_viewModel.myInfo.observe(viewLifecycleOwner) { myPageState ->
            updateUI(myPageState)
        }
    }

    private fun setUserData() {
        val memberId = activity?.intent?.getStringExtra("memberId") ?: "0"
        my_viewModel.getUserInfo(memberId.toInt())
    }

    private fun updateUI(myPageState: MyPageState) {
        if (myPageState.isSuccess) {
            Toast.makeText(context, myPageState.message, Toast.LENGTH_SHORT).show()
            binding.tvMypageId.text = myPageState.userId
            binding.tvMypageName.text = myPageState.userName
            binding.tvMypagePhone.text = myPageState.userPhone
        } else if (myPageState.message.isNotEmpty()) {
            Toast.makeText(context, myPageState.message, Toast.LENGTH_SHORT).show()
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