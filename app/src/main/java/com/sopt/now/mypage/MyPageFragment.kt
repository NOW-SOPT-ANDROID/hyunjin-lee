package com.sopt.now.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sopt.now.data.UserData
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.main.MainViewModel

class MyPageFragment : Fragment(){
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
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
        viewModel.userInfo.observe(viewLifecycleOwner) { user ->
            updateUI(user) // 사용자 데이터 변경 시 UI 업데이트
        }
    }

    private fun updateUI(user: UserData) {
        with(binding) {
            tvMainIdContent.text = user.id
            tvMainPwContent.text = user.pw
            tvMainMbtiContent.text = user.mbti
            tvMainName.text = user.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수 방지를 위해 뷰 바인딩 참조 해제
    }
}