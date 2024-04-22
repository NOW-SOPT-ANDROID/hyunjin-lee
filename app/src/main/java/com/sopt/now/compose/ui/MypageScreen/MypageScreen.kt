package com.sopt.now.compose.ui.MypageScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sopt.now.compose.MyApplication
import com.sopt.now.compose.R
import com.sopt.now.compose.component.AppButton

@Composable
fun MypageScreen(
    myPageViewModel: MyPageViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val userData = myPageViewModel.getUserInfo()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // title
        Text(
            text = stringResource(id = R.string.MYPAGE),
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
            text = stringResource(id = R.string.ID),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userData.id,
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(30.dp))

        // pw
        Text(
            text = stringResource(id = R.string.PW),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userData.pw,
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(30.dp))

        // nickname
        Text(
            text = stringResource(id = R.string.NICKNAME),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userData.name,
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(30.dp))

        // mbti
        Text(
            text = stringResource(id = R.string.MBTI),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userData.mbti,
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(15.dp))

        // 로그아웃 버튼
        AppButton(
            text = stringResource(id = R.string.LOGOUT),
            onClick = {
                MyApplication.prefs.clearUserData()
                navController.navigate("login") {
                    popUpTo("mypage") { inclusive = true }
                }
            },
            padding = PaddingValues(vertical = 10.dp)
        )
    }
}