package com.sopt.now.compose.ui.SignupScreen

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sopt.now.compose.R
import com.sopt.now.compose.component.AppButton
import com.sopt.now.compose.data.auth.SignUpData.RequestSignUpDto

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    signupViewModel: SignUpViewModel = viewModel(),
) {
    val context = LocalContext.current

    var userId by rememberSaveable { mutableStateOf("") }
    var userPw by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }
    var userPhone by rememberSaveable { mutableStateOf("") }

    val signUpState by signupViewModel.signUpState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // title
        Text(
            text = stringResource(id = R.string.SIGNUP),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(50.dp))

        // id
        Text(
            text = stringResource(id = R.string.ID),
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        TextField(
            value = userId,
            onValueChange = { newValue ->
                userId = newValue
            }, // 이 부분 안쓰면 입력안됨
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_ID)) },
            placeholder = { Text(stringResource(id = R.string.ID_daisy)) },
            singleLine = true,
        )
        Spacer(modifier = modifier.height(30.dp))

        // pw
        Text(
            text = stringResource(id = R.string.PW),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        TextField(
            value = userPw,
            onValueChange = { newValue ->
                userPw = newValue
            }, // 이 부분 안쓰면 입력안됨
            modifier = modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_PW)) },
            placeholder = { Text(stringResource(id = R.string.PW_TEXT)) },
            singleLine = true,
        )
        Spacer(modifier = modifier.height(30.dp))

        // nickname
        Text(
            text = stringResource(id = R.string.NICKNAME),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        TextField(
            value = userName,
            onValueChange = { newValue ->
                userName = newValue
            }, // 이 부분 안쓰면 입력안됨
            modifier = modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_NICKNAME)) },
            placeholder = { Text(stringResource(id = R.string.NICKNAME_TEXT)) },
            singleLine = true,
        )
        Spacer(modifier = modifier.height(30.dp))

        // phone
        Text(
            text = stringResource(id = R.string.PHONE),
            fontSize = 20.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        TextField(
            value = userPhone,
            onValueChange = { newValue ->
                userPhone = newValue
            }, // 이 부분 안쓰면 입력안됨
            modifier = modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.INPUT_PHONE)) },
            placeholder = { Text(stringResource(id = R.string.PHONE_TEXT)) },
            singleLine = true,
        )
        Spacer(modifier = modifier.weight(2f))

        // signup button
        AppButton(
            text = stringResource(id = R.string.SIGNUP),
            onClick = {
                signupViewModel.signUp(getSignUpRequestDto(userId, userPw, userName, userPhone))
                Log.d("sign", "$userId, $userPw, $userName, $userPhone")
                if (signUpState.isSuccess) {
                    Toast.makeText(context, signUpState.message, Toast.LENGTH_SHORT).show()
                    // 회원가입 성공 후 로그인 페이지로 이동
                    moveToLogin(signUpState.memberId!!, navController)
                } else {
                    // 실패 메시지 출력
                    Toast.makeText(context, signUpState.message, Toast.LENGTH_SHORT).show()
                }
            },
            padding = PaddingValues(vertical = 10.dp)
        )
    }
}

private fun getSignUpRequestDto(
    userId: String,
    userPw: String,
    userName: String,
    userPhone: String,
): RequestSignUpDto {
    return RequestSignUpDto(
        authenticationId = userId,
        password = userPw,
        nickname = userName,
        phone = userPhone
    )
}

private fun moveToLogin(memberId: String, navController: NavController) {
    val route = "login/$memberId"
    navController.navigate(route) {
        // SignUp 화면을 포함해 이전 모든 화면을 스택에서 제거
        popUpTo("signup") { inclusive = true }
    }
}