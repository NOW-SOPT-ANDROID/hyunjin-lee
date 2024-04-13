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
        items(myprofileList) {
            MyProfileItem(it)
        }
    }
    LazyColumn {
        items(friendList) {
            FriendProfileItem(it)
        }
    }
}