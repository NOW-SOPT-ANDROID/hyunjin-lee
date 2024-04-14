package com.sopt.now.compose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sopt.now.compose.R
import com.sopt.now.compose.viewmodel.UserViewModel


@Composable
fun MypageScreen(
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // title
        Text(
            text = "마이페이지",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(50.dp))

        val painter = painterResource(id = R.drawable.ic_profile_img)
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(100.dp)
                .height(100.dp)
        )

        // id
        Text(
            text = "ID",
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userViewModel.userId.value.toString(),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(30.dp))

        // pw
        Text(
            text = "비밀번호",
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userViewModel.userPw.value.toString(),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(30.dp))

        // nickname
        Text(
            text = "닉네임",
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userViewModel.userName.value.toString(),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(30.dp))

        // mbti
        Text(
            text = "MBTI",
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userViewModel.userMbti.value.toString(),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
    }
}