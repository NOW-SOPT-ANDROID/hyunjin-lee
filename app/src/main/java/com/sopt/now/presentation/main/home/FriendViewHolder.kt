package com.sopt.now.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.now.data.Friend.Friend_
import com.sopt.now.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(friend: Friend_) {
        // 이름, 이메일 바인딩
        binding.tvName.text = "${friend.first_name} ${friend.last_name}"
        binding.tvUserEmail.text = "${friend.email}"

        // Glide를 사용하여 프로필 이미지 로드
        Glide.with(binding.root.context)
            .load(friend.avatar)
            .into(binding.ivProfile)
    }
}