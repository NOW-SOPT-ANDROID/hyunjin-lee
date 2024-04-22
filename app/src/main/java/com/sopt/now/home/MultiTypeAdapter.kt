package com.sopt.now.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Friend
import com.sopt.now.data.UserData
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemMyprofileBinding

class MultiTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<Any>()

    companion object {
        private const val TYPE_FRIEND = 0
        private const val TYPE_USER_DATA = 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Any>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Friend -> TYPE_FRIEND
            is UserData -> TYPE_USER_DATA
            else -> throw IllegalArgumentException("$position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_FRIEND -> {
                val binding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(binding)
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
            is FriendViewHolder -> holder.bind(items[position] as Friend)
            is MyProfileViewHolder -> holder.bind(items[position] as UserData)
        }
    }

    override fun getItemCount(): Int = items.size
}