package com.sopt.now.compose.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sopt.now.compose.viewmodel.UserViewModel


@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel
) {
    var userId by remember { mutableStateOf("") }
    var userPw by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var userMbti by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // title
        Text(
            text = "SIGN UP",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(50.dp))

        // id
        Text(
            text = "ID",
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        TextField(
            value = userId,
            onValueChange = { newValue ->
                userId = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("아이디를 입력해주세요") },
            placeholder = { Text("daisyy") },
            singleLine = true,
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

        TextField(
            value = userPw,
            onValueChange = { newValue ->
                userPw = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = modifier
                .fillMaxWidth(),
            label = { Text("비밀번호를 입력해주세요") },
            placeholder = { Text("123456") },
            singleLine = true,
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

        TextField(
            value = userName,
            onValueChange = { newValue ->
                userName = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = modifier
                .fillMaxWidth(),
            label = { Text("닉네임 입력해주세요") },
            placeholder = { Text("Hyunjin Lee") },
            singleLine = true,
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(30.dp))

        // nickname
        Text(
            text = "MBTI",

            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        TextField(
            value = userMbti,
            onValueChange = { newValue ->
                userMbti = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = modifier
                .fillMaxWidth(),
            label = { Text("닉네임 입력해주세요") },
            placeholder = { Text("entj") },
            singleLine = true,
        )

        // 2 가중치 만큼 빈공간 추가
        Spacer(modifier = modifier.weight(2f))

        // signup button
        val context = LocalContext.current
        Button(
            onClick = {
                if (CheckId(userId) && CheckPw(userPw) && CheckName(userName) && CheckMbti(userMbti)) {
                    // ViewModel에 사용자 정보 저장
                    userViewModel.userId.value = userId
                    userViewModel.userPw.value = userPw
                    userViewModel.userName.value = userName
                    userViewModel.userMbti.value = userMbti

                    Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    navController.navigate("login")
                }
            },
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text("회원가입하기", color = Color.White)
        }
    }
}

fun CheckId(id: String): Boolean {
    if(id.length >= 6 && id.length <= 10){
        return true
    }
    else return false
}

fun CheckPw(pw: String): Boolean {
    if(pw.length >= 8 && pw.length <= 12){
        return true
    }
    else return false
}

fun CheckName(name: String): Boolean {
    if(name.length == 1 && name == " "){
        return false
    }
    else if(name.length >= 1) return true
    else return false
}

fun CheckMbti(mbti: String): Boolean {
    if((mbti.length == 4)
        && (mbti[0].uppercase() == "E" || mbti[0].uppercase() == "I")
        && ((mbti[1].uppercase() == "N" || mbti[1].uppercase() == "S"))
        && ((mbti[2].uppercase() == "T" || mbti[2].uppercase() == "F"))
        && ((mbti[3].uppercase() == "J" || mbti[3].uppercase() == "P"))
    )
        return true
    else return false
}