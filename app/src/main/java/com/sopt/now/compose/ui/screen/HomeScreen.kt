package com.sopt.now.compose.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.sopt.now.compose.data.FriendProfileItem
import com.sopt.now.compose.data.friendList

@Composable
fun HomeScreen() {
    LazyColumn {
        items(friendList) {
            FriendProfileItem(it)
        }
    }
}