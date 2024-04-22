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
import com.sopt.now.data.Friend
import com.sopt.now.data.UserData
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.main.MainViewModel

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private val mainViewModel: MainViewModel by activityViewModels() // MainViewModel에서 userInfo 가져옴
    private val multiTypeAdapter = MultiTypeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.userInfo.observe(viewLifecycleOwner) { userData ->
            setupRecyclerView(userData)
        }
    }
    private fun setupRecyclerView(userData: UserData){
        val items = mutableListOf<Any>().apply {
            // UserData를 추가
            add(userData)
            // 친구 목록을 추가
            addAll(viewModel.mockFriendList)
        }
        multiTypeAdapter.setItems(items)

        // RecyclerView 설정
        with(binding.rvMyprofile) {
            layoutManager = LinearLayoutManager(context)
            adapter = multiTypeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수 방지를 위해 뷰 바인딩 참조 해제
    }
}