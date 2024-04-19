package com.sopt.now.compose.ui.LoginScreen

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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sopt.now.compose.R
import com.sopt.now.compose.ui.SignupScreen.UserViewModel

@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
    ) {
        // 20.dp만큼 빈공간 추가
        Spacer(modifier = Modifier.padding(20.dp))

        // title
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        // 1.5가중치 만큼 빈공간 추가
        Spacer(modifier = Modifier.weight(1f))

        // id
        Text(
            text = stringResource(id = R.string.ID)
        )

        TextField(
            value = userViewModel.userId.value.toString(),
            onValueChange = { userViewModel.userId.value = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_ID)) },
            placeholder = { Text(stringResource(id = R.string.ID_daisy)) },
            singleLine = true,
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = Modifier.height(30.dp))

        // pw
        Text(
            text = stringResource(id = R.string.PW)
        )

        TextField(
            value = userViewModel.userPw.value.toString(),
            onValueChange = { userViewModel.userPw.value = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_PW)) },
            placeholder = { Text(stringResource(id = R.string.PW_TEXT)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(), // 비밀번호 마스킹
        )

        // 2 가중치 만큼 빈공간 추가
        Spacer(modifier = Modifier.weight(2f))

        // signup button
        TextButton(
            onClick = { /* 클릭 시 수행될 동작 */
                Toast.makeText(context, R.string.SIGNUP, Toast.LENGTH_SHORT).show()
                navController.navigate("signup")
            },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.SIGNUP),
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                textDecoration = TextDecoration.Underline,
            )
        }

        // login button
        Button(
            onClick = {
                /* 클릭 시 수행될 동작 */
                if (isValidLogin(userViewModel.userId.value.toString(), userViewModel.userPw.value.toString(),
                        userViewModel.userId.value.toString(), userViewModel.userPw.value.toString())
                ) {
                    // 로그인 성공 시
                    Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                    navController.navigate("mypage")
                } else {
                    // 로그인 실패 시
                    Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(stringResource(id = R.string.LOGIN), color = Color.White)
        }
    }
}

// 로그인 검증 함수
fun isValidLogin(userId: String?, userPw: String?, getId: String, getPw: String): Boolean {
    return userId != "" && userPw != "" && userId == getId && userPw == getPw
}