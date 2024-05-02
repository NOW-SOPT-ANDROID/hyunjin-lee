package com.sopt.now.compose.ui.HomeScreen.Item

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.data.auth.User.UserProfile

val friendList = listOf<UserProfile.Friend>(
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "이의경",
        selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지?",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "우상욱",
        selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "배지현",
        selfDescription = "표정 풀자 ^^",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "강문수",
        selfDescription = "오빠 얼른 와서 신랄한 비판 ㄱㄱ",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "이유빈",
        selfDescription = "난 언제쯤 유빈이만큼 잘할 수 있을까,,",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "이나경",
        selfDescription = "나경아 우리 다음 세미나때는 볼 수 있는 거지?",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "김언지",
        selfDescription = "언지야 사랑해",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "손민재",
        selfDescription = "큐브 좀 그만해라",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "이석준",
        selfDescription = "이석찬이랑 형제지?",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "이석찬",
        selfDescription = "이석준이랑 형제지?",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "wave to earth",
        selfDescription = "이석준이랑 형제지?",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "touched",
        selfDescription = "이석준이랑 형제지?",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "rockstar",
        selfDescription = "이석준이랑 형제지?",
    ),
    UserProfile.Friend(
        profileImage = Icons.Filled.Person,
        name = "apple",
        selfDescription = "이석준이랑 형제지?",
    ),
)

@Composable
fun FriendProfileItem(friend: UserProfile.Friend) {
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