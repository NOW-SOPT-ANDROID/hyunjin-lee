package com.sopt.now.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Friend
import com.sopt.now.data.UserData
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemMyprofileBinding

class MultiTypeAdapter(private val context: Context, private val onFriendLongClick: (Friend) -> Unit) : ListAdapter<Any, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Friend -> TYPE_FRIEND
            is UserData -> TYPE_USER_DATA
            else -> throw IllegalArgumentException("$position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) { // 뷰 type에 따라 다른 뷰홀더 생성
            TYPE_FRIEND -> {
                val binding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(binding, onFriendLongClick)
            }
            TYPE_USER_DATA -> {
                val binding = ItemMyprofileBinding.inflate(inflater, parent, false)
                MyProfileViewHolder(binding)
            }
            else -> throw IllegalArgumentException("땡")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FriendViewHolder -> holder.bind(getItem(position) as Friend)
            is MyProfileViewHolder -> holder.bind(getItem(position) as UserData)
        }
    }

    companion object {
        private const val TYPE_FRIEND = 0
        private const val TYPE_USER_DATA = 1
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return when {
                    oldItem is Friend && newItem is Friend -> oldItem.friend_id == newItem.friend_id
                    oldItem is UserData && newItem is UserData -> oldItem.id == newItem.id
                    else -> false
                }
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }
        }
    }
}