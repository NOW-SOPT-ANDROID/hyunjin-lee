package com.sopt.now.compose.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.sopt.now.compose.ui.Item.FriendProfileItem
import com.sopt.now.compose.ui.Item.MyProfileItem
import com.sopt.now.compose.ui.Item.friendList
import com.sopt.now.compose.ui.Item.myprofileList

@Composable
fun HomeScreen() {
    LazyColumn {
        // 내 프로필 아이템 리스트
        items(myprofileList) {
            MyProfileItem(it)
        }

        // 친구 목록 아이템 리스트
        items(friendList) {
            FriendProfileItem(it)
        }
    }
}