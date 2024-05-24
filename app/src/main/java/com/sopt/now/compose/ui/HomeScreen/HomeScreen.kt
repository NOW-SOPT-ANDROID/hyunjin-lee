package com.sopt.now.compose.ui.HomeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sopt.now.compose.BuildConfig.AUTH_FRIEND_URL
import com.sopt.now.compose.data.friend.Friend_


@Composable
fun HomeScreen(viewModel: FriendViewModel) {
    val friends by remember { viewModel.users }.observeAsState(initial = emptyList())
    UsersList(friends = friends)
}

@Composable
fun UsersList(friends: List<Friend_>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(friends) { friend ->
            FriendItem(friend)
        }
    }
}

@Composable
fun FriendItem(friend: Friend_) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Name: ${friend.first_name} ${friend.last_name}")
        Text(text = "Email: ${friend.email}")
        Image(
            painter = rememberImagePainter(
                data = friend.avatar
            ),
            contentDescription = "Android profileImage",
            modifier = Modifier.size(100.dp)
        )
        Log.d("friend", AUTH_FRIEND_URL + friend.avatar)
    }
}

