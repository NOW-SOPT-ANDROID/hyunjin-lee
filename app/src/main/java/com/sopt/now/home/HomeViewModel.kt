package com.sopt.now.home

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.Friend

class HomeViewModel : ViewModel() {
    val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.ic_profile_img,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지?",
        ),
        Friend(
            profileImage = R.drawable.ic_profile_img,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        Friend(
            profileImage = R.drawable.ic_profile_img,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        Friend(
            profileImage = R.drawable.ic_profile_img,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지?",
        ),
        Friend(
            profileImage = R.drawable.ic_profile_img,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        Friend(
            profileImage = R.drawable.ic_profile_img,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        Friend(
            profileImage = R.drawable.ic_profile_img,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지?",
        ),
        Friend(
            profileImage = R.drawable.ic_profile_img,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        Friend(
            profileImage = R.drawable.ic_profile_img,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
    )
}