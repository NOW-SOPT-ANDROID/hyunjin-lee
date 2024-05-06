package com.sopt.now.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.data.Friend.ResponseFriendDto
import com.sopt.now.data.ServicePool
import com.sopt.now.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val friendListAdapter by lazy { FriendListAdapter() }

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
        loadFriends(1)
    }

    private fun setupRecyclerView() {
        val columnCount = 2 // 원하는 컬럼 수
        binding.rvMyprofile.apply {
            layoutManager = GridLayoutManager(context, columnCount)
            adapter = friendListAdapter
        }
    }

    private fun setupPageButtons(totalPages: Int) {
        binding.llPageButtons.removeAllViews() // 이전에 추가된 버튼들을 제거
        for (i in 1..totalPages) {
            val pageNumber = i
            val button = Button(context).apply {
                text = pageNumber.toString()
                setOnClickListener {
                    loadFriends(pageNumber)
                }
            }
            binding.llPageButtons.addView(button) // 페이지 버튼을 LinearLayout에 추가
        }
    }

    private fun loadFriends(pageNumber: Int) {
        ServicePool.friendService.getUsers(pageNumber).enqueue(object : Callback<ResponseFriendDto> {
            override fun onResponse(call: Call<ResponseFriendDto>, response: Response<ResponseFriendDto>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        friendListAdapter.submitList(it.data)
                        setupPageButtons(it.total_pages) // 페이지 버튼 설정
                    }
                } else {
                    Snackbar.make(binding.root, R.string.fail_data, LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseFriendDto>, t: Throwable) {
                Snackbar.make(binding.root, R.string.fail_network, LENGTH_SHORT).show()
            }
        })
    }

    fun scrollToTop() {
        binding.rvMyprofile.scrollToPosition(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}