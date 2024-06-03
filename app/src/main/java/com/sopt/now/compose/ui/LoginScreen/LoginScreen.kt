package com.sopt.now.compose.ui.LoginScreen

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.sopt.now.compose.MainViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.component.AppButton
import com.sopt.now.compose.data.auth.LoginData.LoginState
import com.sopt.now.compose.data.auth.LoginData.RequestLoginDto
import com.sopt.now.compose.data.auth.User.UserState
import com.sopt.now.compose.util.modifier.noRippleClickable

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    mainViewModel: MainViewModel,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
) {
    val context = LocalContext.current
    var expanded = remember { mutableStateOf(false) }

    // 로그인 상태 관찰
    val loginState by loginViewModel.loginLiveData.observeAsState(initial = LoginState(false, ""))

    // 사용자 정보 상태 관찰
    val userState by mainViewModel.userInfo.collectAsState(
        initial = UserState(
            isSuccess = false,
            ""
        )
    )

    // 기본값으로 회원가입에서 받아온 값을 사용하되, 사용자 입력을 통해 업데이트될 수 있도록 remember 사용
    var userId by rememberSaveable { mutableStateOf("") }
    var userPw by rememberSaveable { mutableStateOf("") }

    // 회원 정보 조회
    val memberId = navBackStackEntry.arguments?.getString("memberId") ?: ""
    Log.d("loginscreen", memberId)
    LaunchedEffect(memberId) {
        if (memberId.isNotEmpty()) {
            mainViewModel.getUserInfo(memberId)
        }
    }

    LaunchedEffect(userState) {
        if (userState.isSuccess) {
            // 사용자 아이디를 EditText에 설정
            userId = userState.userId ?: ""
            // 회원 조회 성공 메시지 표시
            Toast.makeText(context, userState.message, Toast.LENGTH_SHORT).show()
        } else if (userState.userId.isNullOrEmpty().not()) {
            // 회원 조회 실패 메시지 표시
            Toast.makeText(context, userState.message, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .noRippleClickable {
                expanded.value = !expanded.value
            }
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
        Spacer(modifier = Modifier.weight(0.5f))

        // id
        Text(
            text = stringResource(id = R.string.ID)
        )
        TextField(
            value = userId,
            onValueChange = { userId = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_ID)) },
            placeholder = { Text(stringResource(id = R.string.ID_daisy)) },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(30.dp))

        // pw
        Text(
            text = stringResource(id = R.string.PW)
        )
        TextField(
            value = userPw,
            onValueChange = { userPw = it },
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
            onClick = { // 회원가입 버튼 클릭시
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
            onClick = { // 로그인 버튼 클릭시
                loginViewModel.login(getLoginRequestDto(userId, userPw))

                if (loginState.isSuccess) {
                    Toast.makeText(context, loginState.message, Toast.LENGTH_SHORT).show()
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                } else if (loginState.message.isNotEmpty()) {
                    Toast.makeText(context, loginState.message, Toast.LENGTH_SHORT).show()
                }
            },
            padding = PaddingValues(vertical = 10.dp)
        )
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

fun getLoginRequestDto(userId: String, userPw: String): RequestLoginDto {
    return RequestLoginDto(
        authenticationId = userId,
        password = userPw,
    )
}