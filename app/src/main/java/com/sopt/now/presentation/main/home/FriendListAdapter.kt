package com.sopt.now.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.data.Friend.Friend_
import com.sopt.now.databinding.ItemFriendBinding


class FriendListAdapter : ListAdapter<Friend_, FriendViewHolder>(DIFF_CALLBACK) { // friend 타입의 데이터를, friendviewholder를 이용해서 화면에 표시
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(layoutInflater, parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) { // 각 항목에 대한 데이터를 ViewHolder에 바인딩
        val friend = getItem(position) // 현재 position에 해당하는 Friend_ 객체 불러옴
        holder.bind(friend)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Friend_>() {
            // 두 항목의 ID가 같은지 비교
            override fun areItemsTheSame(oldItem: Friend_, newItem: Friend_): Boolean {
                return oldItem.id == newItem.id
            }

            // 두 항목의 내용이 같은지 비교하여 데이터가 변하지 않았는지 확인
            override fun areContentsTheSame(oldItem: Friend_, newItem: Friend_): Boolean {
                return oldItem == newItem
            }
        }
    }
}
