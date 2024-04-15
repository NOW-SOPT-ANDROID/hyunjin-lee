package com.sopt.now.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.core.UserData
import com.sopt.now.R
import com.sopt.now.data.Friend
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.main.MainViewModel

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private val mainViewModel: MainViewModel by activityViewModels() // MainViewModel에서 userInfo 가져옴

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // MultiTypeAdapter 초기화
        val multiTypeAdapter = MultiTypeAdapter()

        // MyProfile 어댑터 초기화 및 설정
        mainViewModel.userInfo.observe(viewLifecycleOwner) { user ->
            // 사용자 정보와 친구 목록을 합친 리스트 생성
            val items = listOf(user) + viewModel.mockFriendList
            multiTypeAdapter.setItems(items)
        }

        // RecyclerView 설정
        binding.rvMyprofile.adapter = multiTypeAdapter
        binding.rvMyprofile.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수 방지를 위해 뷰 바인딩 참조 해제
    }
}
