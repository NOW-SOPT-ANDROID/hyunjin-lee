package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class SignUpActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpScreen()            }
            }
        }
    }
}

data class SignUpData(
    val userId: String,
    val userPw: String,
    val userName: String,
    val userMbti: String
)

@Composable
fun SignUpScreen(navController: NavHostController = rememberNavController()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var userId by remember { mutableStateOf("") }
        var userPw by remember { mutableStateOf("") }
        var userName by remember { mutableStateOf("") }
        var userMbti by remember { mutableStateOf("") }

        // title
        Text(
            text = "SIGN UP",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = Modifier.height(50.dp))

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
            placeholder = { Text("Hyunjin Lee") },
            singleLine = true,
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = Modifier.height(30.dp))

        // pw
        Text(
            text = "비밀번호",

            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        TextField(
            value = userPw,
            onValueChange = { newValue ->
                userPw = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("비밀번호를 입력해주세요") },
            placeholder = { Text("Hyunjin Lee") },
            singleLine = true,
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = Modifier.height(30.dp))

        // nickname
        Text(
            text = "닉네임",

            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        TextField(
            value = userName,
            onValueChange = { newValue ->
                userName = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("닉네임 입력해주세요") },
            placeholder = { Text("Hyunjin Lee") },
            singleLine = true,
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = Modifier.height(30.dp))

        // nickname
        Text(
            text = "MBTI",

            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        TextField(
            value = userMbti,
            onValueChange = { newValue ->
                userMbti = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("닉네임 입력해주세요") },
            placeholder = { Text("Hyunjin Lee") },
            singleLine = true,
        )

        // 2 가중치 만큼 빈공간 추가
        Spacer(modifier = Modifier.weight(2f))

        // button
        val context = LocalContext.current
        Button(
            onClick = {
                if (CheckId(userId) && CheckPw(userPw) && CheckName(userName) && CheckMbti(userMbti)) {
                    Toast.makeText(context, "회원가입 완료", Toast.LENGTH_SHORT).show()

                    val intent = Intent(context, LoginActivity::class.java).apply {
                        putExtra("id", userId)
                        putExtra("pw", userPw)
                        putExtra("name", userName)
                        putExtra("mbti", userMbti)
                    }
                    context.startActivity(intent)

                    // 로그인 화면으로 이동
                    navController.navigate("LoginScreen") {
                        // 회원가입 화면을 back stack에서 제거
                        // 사용자가 로그인 화면에서 뒤로 가기 눌렀을 때 회원가입 화면으로 돌아가지 않음
                        popUpTo("SignUpScreen") { inclusive = true }
                    }
                }
            },
            modifier = Modifier
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
        && ((mbti[1].uppercase() == "N" || mbti[0].uppercase() == "S"))
        && ((mbti[2].uppercase() == "T" || mbti[0].uppercase() == "F"))
        && ((mbti[3].uppercase() == "J" || mbti[0].uppercase() == "P"))
    )
        return true
    else return false
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    NOWSOPTAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            SignUpScreen()
        }
    }
}