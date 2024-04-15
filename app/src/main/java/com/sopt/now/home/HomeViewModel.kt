package com.sopt.now.home

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.Friend

class HomeViewModel : ViewModel() {
    val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "이의경",
            selfDescription = "의진아 고생이 많다,, ^_^",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "우상욱",
            selfDescription = "손흥민",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "모루인형 만들어줄게 좀만 기다려",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "강문수",
            selfDescription = "오빠 얼른 와서 신랄한 비판 ㄱㄱ",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "이유빈",
            selfDescription = "난 언제쯤 유빈이만큼 잘할 수 있을까,,",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "이나경",
            selfDescription = "나경아 우리 다음 세미나때는 볼 수 있는 거지?",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "김언지",
            selfDescription = "언지야 사랑해",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "손민재",
            selfDescription = "큐브 좀 그만해라",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "이석준",
            selfDescription = "이석찬이랑 형제지?",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "이석찬",
            selfDescription = "이석준이랑 형제지?",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "신민석",
            selfDescription = "운팀 회식 안빠질 거지?",
        ),
    )
}