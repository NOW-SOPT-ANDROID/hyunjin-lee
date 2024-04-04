package com.sopt.now.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getuserId = intent.getStringExtra("id") ?: ""
        val getuserPw = intent.getStringExtra("pw") ?: ""
        val getuserName = intent.getStringExtra("name") ?: ""
        val getuserMbti = intent.getStringExtra("mbti") ?: ""

        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(getId=getuserId, getPw=getuserPw, getName=getuserName, getMbti=getuserMbti)
                }
            }
        }
    }
}


@Composable
fun LoginScreen(
    getId: String,
    getPw: String,
    getName: String,
    getMbti: String,
    navController: NavHostController = rememberNavController(),
){
    // 기본값을 인자로 받은 값으로 설정
    var userId by remember { mutableStateOf(getId) }
    var userPw by remember { mutableStateOf(getPw) }
    var userName by remember { mutableStateOf(getName) }
    var userMbti by remember { mutableStateOf(getMbti) }

    Log.d("main-userinfo", "${userId}, ${userPw}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
    ) {
        // 20.dp만큼 빈공간 추가
        Spacer(modifier = Modifier.padding(20.dp))

        // title
        Text(
            text = "Welcome To SOPT",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        // 1.5가중치 만큼 빈공간 추가
        Spacer(modifier = Modifier.weight(1f))

        // id
        Text(
            text = "ID",
        )

        TextField(
            value = userId,
            onValueChange = { userId = it },
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
        )

        TextField(
            value = userPw,
            onValueChange = { userPw = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("비밀번호를 입력해주세요") },
            placeholder = { Text("Hyunjin Lee") },
            singleLine = true,
        )

        // 2 가중치 만큼 빈공간 추가
        Spacer(modifier = Modifier.weight(2f))

        // signup
        val context = LocalContext.current
        // signup button
        TextButton(
            onClick = { /* 클릭 시 수행될 동작 */
                Toast.makeText(context, "회원가입하기", Toast.LENGTH_SHORT).show()
                navController.navigate("SignUpScreen")
            },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = "회원가입",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                textDecoration = TextDecoration.Underline,
            )
        }

        // login button
        Button(
            onClick = { /* 클릭 시 수행될 동작 */
                // 사용 예
                if (isValidLogin(userId, userPw, getId, getPw)) {
                    moveToMainActivity(context, userId, userPw, userName, userMbti)
                    navController.navigate("MainScreen")
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text("로그인하기", color = Color.White)
        }
    }
}

// 로그인 검증 함수
fun isValidLogin(userId: String?, userPw: String?, getId: String, getPw: String): Boolean {
    return userId != "" && userPw != "" && userId == getId && userPw == getPw
}

// 메인 액티비티로 이동하는 함수
fun moveToMainActivity(context: Context, userId: String, userPw: String, userName: String?, userMbti: String?) {
    Toast.makeText(context, "로그인하기", Toast.LENGTH_SHORT).show()

    val intent = Intent(context, MainPageActivity::class.java).apply {
        putExtra("id", userId)
        putExtra("pw", userPw)
        putExtra("name", userName)
        putExtra("mbti", userMbti)
    }
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    NOWSOPTAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            LoginScreen(getId = "", getPw = "", getName = "", getMbti = "")
        }
    }
}