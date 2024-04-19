package com.sopt.now.compose.ui.HomeScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.sopt.now.compose.ui.Item.FriendProfileItem
import com.sopt.now.compose.ui.Item.MyProfileItem
import com.sopt.now.compose.ui.Item.friendList
import com.sopt.now.compose.ui.Item.myprofile

@Composable
fun HomeScreen() {
    LazyColumn {
        // 내 프로필 아이템
        item {
            MyProfileItem(myprofile) // myprofile을 호출하기 위해 item 블록 안에 넣어줍니다.
        }

        // 친구 목록 아이템 리스트
        items(friendList) {
            FriendProfileItem(it)
        }
    }
}