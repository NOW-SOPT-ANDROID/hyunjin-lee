package com.sopt.now.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Friend
import com.sopt.now.databinding.ItemFriendBinding

class FriendAdapter(requireContext: Context) : RecyclerView.Adapter<FriendViewHolder>() {
    // 임시의 빈 리스트
    private var friendList: List<Friend> = emptyList()

    // ViewHolder에 들어갈 Item View를 만들어주는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendViewHolder(binding)
    }

    // ViewHolder에 데이터를 매칭하는 함수
    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.onBind(friendList[position])
    }

    // 데이터 리스트의 아이템 갯수는 몇 개인지
    override fun getItemCount() = friendList.size

    fun setFriendList(friendList: List<Friend>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }
}