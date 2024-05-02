package com.sopt.now.compose.ui.MypageScreen

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sopt.now.compose.R
import com.sopt.now.compose.component.AppButton
import com.sopt.now.compose.data.mypage.MyPageState

@Composable
fun MypageScreen(
    myPageViewModel: MyPageViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val context = LocalContext.current

    // 사용자 정보 상태 관찰
    val myPageState by myPageViewModel.myInfo.collectAsState(initial = MyPageState(isSuccess = false, ""))

    var userId by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }
    var userPhone by rememberSaveable { mutableStateOf("") }

    if (myPageState.isSuccess) {
        Toast.makeText(context, myPageState.message, Toast.LENGTH_SHORT).show()
        userId = myPageState.userId ?: ""
        userName = myPageState.userName ?: ""
        userPhone = myPageState.userPhone ?: ""
    } else if (myPageState.message.isNotEmpty()) {
        Toast.makeText(context, myPageState.message, Toast.LENGTH_SHORT).show()
    }

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
            text = userId,
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = modifier.height(30.dp))

        // pw
        Text(
            text = stringResource(id = R.string.PW),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userName,
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = modifier.height(30.dp))

        // nickname
        Text(
            text = stringResource(id = R.string.NICKNAME),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userPhone,
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = modifier.height(30.dp))

        // mbti
        Text(
            text = stringResource(id = R.string.PHONE),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(15.dp))

        // 로그아웃 버튼
        AppButton(
            text = stringResource(id = R.string.LOGOUT),
            onClick = {
                navController.navigate("login") {
                    popUpTo("mypage") { inclusive = true }
                }
            },
            padding = PaddingValues(vertical = 10.dp)
        )
    }
}