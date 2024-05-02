package com.sopt.now.compose.ui.HomeScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sopt.now.compose.ui.HomeScreen.Item.FriendProfileItem
import com.sopt.now.compose.ui.HomeScreen.Item.MyProfileItem
import com.sopt.now.compose.ui.HomeScreen.Item.friendList
import com.sopt.now.compose.ui.HomeScreen.Item.myprofile

@Composable
fun HomeScreen() {
    val groupedByInitial = friendList.groupBy { friend ->
        extractInitialSimple(friend.name.first())
    }.mapValues { (_, friends) ->
        friends.sortedBy { friend -> // 각 그룹 내에서 영어 이름 순으로 정렬
            friend.name.filter { it.isLetter() } // 이름에서 문자만 추출하여 정렬 기준으로 사용
        }
    }.toSortedMap() // 초성으로 정렬

    LazyColumn {
        // 내 프로필 아이템
        item {
            MyProfileItem(myprofile) // myprofile을 호출하기 위해 item 블록 안에 넣어줍니다.
        }

        // 초성별로 그룹화된 친구 목록 아이템 리스트
        groupedByInitial.forEach { (initial, friends) ->
            // 초성 제목
            item {
                Text(text = initial.toString(), style = MaterialTheme.typography.titleMedium)
            }
            // 해당 초성을 가진 친구 목록
            items(friends) { friend ->
                FriendProfileItem(friend)
            }
        }
    }
}

private fun extractInitialSimple(char: Char): Char {
    val initialMap = mapOf(
        '가'..'깋' to 'ㄱ', '나'..'닣' to 'ㄴ', '다'..'딯' to 'ㄷ', '라'..'맇' to 'ㄹ',
        '마'..'밓' to 'ㅁ', '바'..'빟' to 'ㅂ', '사'..'싷' to 'ㅅ', '아'..'잏' to 'ㅇ',
        '자'..'짛' to 'ㅈ', '차'..'칳' to 'ㅊ', '카'..'킿' to 'ㅋ', '타'..'팋' to 'ㅌ',
        '파'..'핗' to 'ㅍ', '하'..'힣' to 'ㅎ'
    )
    for ((range, initial) in initialMap) {
        if (char in range) return initial
    }
    return char
}
