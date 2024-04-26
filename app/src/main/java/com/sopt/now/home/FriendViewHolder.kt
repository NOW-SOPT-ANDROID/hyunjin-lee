package com.sopt.now.home

import android.app.AlertDialog
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.sopt.now.R
import com.sopt.now.data.Friend
import com.sopt.now.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding, private val onFriendLongClick: (Friend) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(friend: Friend) {
        // 바인딩 로직
        binding.run {
            ivProfile.setImageResource(friend.profileImage)
            tvName.text = friend.name
            tvSelfDescription.text = friend.music
        }

        itemView.setOnLongClickListener {
            dialogRemoveFriend(itemView.context, friend)
            true
        }
    }

    private fun dialogRemoveFriend(context: Context, friend: Friend) {
        AlertDialog.Builder(context)
            .setView(R.layout.dialog_removefriend)
            .show()
            .also { alertDialog ->
                if (alertDialog == null) {
                    return@also
                }

                val button_accept =
                    alertDialog.findViewById<MaterialButton>(R.id.bt_removedialog_approve_button)
                val button_cancel =
                    alertDialog.findViewById<MaterialButton>(R.id.bt_removedialog_cancel_button)

                button_accept?.setOnClickListener {
                    alertDialog.dismiss()
                    onFriendLongClick(friend)
                }
                button_cancel?.setOnClickListener {
                    alertDialog.dismiss()
                }
            }
    }
}