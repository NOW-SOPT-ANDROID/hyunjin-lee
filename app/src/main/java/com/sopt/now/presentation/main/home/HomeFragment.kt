package com.sopt.now.presentation.main.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.sopt.now.R
import com.sopt.now.data.Friend.Friend
import com.sopt.now.data.User.UserData
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.presentation.main.MainViewModel

class HomeFragment: Fragment() {
    private var _homebinding: FragmentHomeBinding? = null
    private val homebinding get() = _homebinding!!
    private val homeviewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(requireActivity().application)
    }
    private val mainViewModel: MainViewModel by activityViewModels() // MainViewModel에서 userInfo 가져옴
    private val multiTypeAdapter by lazy {
        MultiTypeAdapter(requireContext()) { friend ->
            homeviewModel.removeFriend(friend)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _homebinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homebinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        dialogAddFriend()
    }

    private fun observeViewModel() {
        // User 정보에 대한 관찰
        mainViewModel.userInfo.observe(viewLifecycleOwner) { userData ->
            // userData가 null이 아닐 때만 setupRecyclerView 호출
            userData?.let {
                setupRecyclerView(it)
            }
        }

        // 친구 목록에 대한 관찰
        homeviewModel.mockFriendList.observe(viewLifecycleOwner) { friendsList ->
            updateRecyclerView(friendsList)
        }
    }

    private fun updateRecyclerView(friendsList: List<Friend>) {
        val userData = mainViewModel.userInfo.value ?: return
        setupRecyclerView(userData, friendsList)
    }

    private fun setupRecyclerView(userData: UserData, friendsList: List<Friend> = emptyList()){
        val items = mutableListOf<Any>().apply {
            add(userData) // UserData를 추가
            addAll(friendsList) // 친구 목록을 추가
        }
        multiTypeAdapter.submitList(items.toList())

        // RecyclerView 설정
        with(homebinding.rvMyprofile) {
            layoutManager = LinearLayoutManager(context)
            adapter = multiTypeAdapter
        }
    }

    fun scrollToTop() {
        homebinding.rvMyprofile.scrollToPosition(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _homebinding = null // 메모리 누수 방지를 위해 뷰 바인딩 참조 해제
    }

    private fun dialogAddFriend() {
        homebinding.btHomeFloatingActionButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setView(R.layout.dialog_addfriend)
                .show()
                .also { alertDialog ->

                    if (alertDialog == null) {
                        return@also
                    }

                    val userName = alertDialog.findViewById<EditText>(R.id.et_dialog_username)?.text
                    val profilemusic =
                        alertDialog.findViewById<EditText>(R.id.et_dialog_profilemusic)?.text
                    val button_accept =
                        alertDialog.findViewById<MaterialButton>(R.id.bt_dialog_approve_button)
                    val button_cancel =
                        alertDialog.findViewById<MaterialButton>(R.id.bt_dialog_cancel_button)

                    button_accept?.setOnClickListener {
                        if (userName.toString().isNotBlank() && profilemusic.toString()
                                .isNotBlank()
                        ) {
                            alertDialog.dismiss()
                            homeviewModel.addFriend(
                                Friend(
                                    profileImage = R.drawable.ic_person_black_24,
                                    name = userName.toString(),
                                    music = profilemusic.toString()
                                )
                            )
                        }
                    }
                    button_cancel?.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
        }
    }
}