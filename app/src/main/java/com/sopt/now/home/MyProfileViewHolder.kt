package com.sopt.now.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.UserData
import com.sopt.now.databinding.ItemMyprofileBinding

class MyProfileViewHolder(private val binding: ItemMyprofileBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(userdata: UserData) {
        binding.run {
            ivMyprofile.setImageResource(userdata.userprofileImage!!)
            tvMyname.text = userdata.username
            tvMymusic.text = "wave to earth - daisy"
        }
    }
}