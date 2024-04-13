package com.sopt.now.compose.data

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Friend(
    val profileImage: ImageVector,
    val name: String,
    val selfDescription: String,
)

val friendList = listOf<Friend>(
    Friend(
        profileImage = Icons.Filled.Person,
        name = "이의경",
        selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지?",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "우상욱",
        selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "배지현",
        selfDescription = "표정 풀자 ^^",
    ),
)

@Composable
fun FriendProfileItem(friend: Friend) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = friend.profileImage,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = friend.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = friend.selfDescription,
            fontSize = 14.sp,
        )
    }
}

