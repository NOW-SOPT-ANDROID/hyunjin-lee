package com.sopt.now.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainPageActivity : ComponentActivity() {
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
                    MainScreen(getId = getuserId, getPw = getuserPw, getName = getuserName, getMbti = getuserMbti)
                }
            }
        }
    }
}


@Composable
fun MainScreen(
    getId: String,
    getPw: String,
    getName: String,
    getMbti: String,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 인자로 전달받은 signUpData를 가져옴
        val userIdArg = getId
        val userPwArg = getPw
        val userNameArg = getName
        val userMbtiArg = getMbti

        var userId by remember { mutableStateOf(userIdArg) }
        var userPw by remember { mutableStateOf(userPwArg) }
        var userName by remember { mutableStateOf(userNameArg) }
        var userMbti by remember { mutableStateOf(userMbtiArg) }

        Log.d("main", "${userId}, ${userPw}")

        // title
        Text(
            text = "하.. 분명 이게 아닌데..",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = Modifier.height(50.dp))

        val painter = painterResource(id = R.drawable.ic_profile_img)
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )

        // id
        Text(
            text = "ID",
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userId,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
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

        Text(
            text = userPw,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
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

        Text(
            text = userName,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = Modifier.height(30.dp))

        // mbti
        Text(
            text = "MBTI",
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Text(
            text = userMbti,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
    }
}