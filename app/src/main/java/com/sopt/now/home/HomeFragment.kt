package com.sopt.now.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        // MultiTypeAdapter 초기화
        val multiTypeAdapter = MultiTypeAdapter()
        val userData = mainViewModel.getUserInfo()

        // 사용자 데이터와 친구 목록을 합쳐서 하나의 리스트로 만듭니다.
        val items = mutableListOf<Any>().apply {
            // UserData를 추가
            add(userData)
            // 친구 목록을 추가
            addAll(viewModel.mockFriendList)
        }
        multiTypeAdapter.setItems(items)
        Log.d("home", "$items")

        // RecyclerView 설정
        binding.rvMyprofile.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = multiTypeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수 방지를 위해 뷰 바인딩 참조 해제
    }
}