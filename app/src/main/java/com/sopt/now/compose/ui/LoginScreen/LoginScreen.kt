package com.sopt.now.compose.ui.LoginScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sopt.now.compose.R
import com.sopt.now.compose.component.AppButton
import com.sopt.now.compose.ui.SignupScreen.UserViewModel
import com.sopt.now.compose.util.modifier.noRippleClickable

@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val context = LocalContext.current
    var expanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.noRippleClickable {
            expanded.value = !expanded.value
        }.fillMaxSize()
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

        Spacer(modifier = Modifier.weight(0.5f))

        // id
        Text(
            text = stringResource(id = R.string.ID)
        )

        TextField(
            value = userViewModel.userId.value, // MutableLiveData 대신 MutableState 사용
            onValueChange = { newValue ->
                userViewModel.userId.value = newValue
            },
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
            value = userViewModel.userPw.value, // MutableLiveData 대신 MutableState 사용
            onValueChange = { newValue ->
                userViewModel.userPw.value = newValue
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_PW)) },
            placeholder = { Text(stringResource(id = R.string.PW_TEXT)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(), // 비밀번호 마스킹
        )

        Spacer(modifier = Modifier.weight(1.0f))

        // signup button
        AppButton(
            text = stringResource(id = R.string.SIGNUP),
            onClick = {
                Toast.makeText(context, R.string.SIGNUP, Toast.LENGTH_SHORT).show()
                navController.navigate("signup") {
                    popUpTo("login") { inclusive = true }
                }
            },
            padding = PaddingValues(vertical = 10.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // login button
        AppButton(
            text = stringResource(id = R.string.LOGIN),
            onClick = {
                if (isValidLogin(userViewModel.userId.value, userViewModel.userPw.value,
                        userViewModel.userId.value, userViewModel.userPw.value)
                ) {
                    Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            },
            padding = PaddingValues(vertical = 10.dp)
        )

        Spacer(modifier = Modifier.weight(0.5f))
    }
}

// 로그인 검증 함수
fun isValidLogin(userId: String?, userPw: String?, getId: String, getPw: String): Boolean {
    return userId != "" && userPw != "" && userId == getId && userPw == getPw
}