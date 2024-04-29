package com.sopt.now.compose.ui.SignupScreen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.sopt.now.compose.data.UserData


@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    signupViewModel: SignUpViewModel = viewModel()
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
            text = stringResource(id = R.string.SIGNUP),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // 높이 30dp인 공간 추가
        Spacer(modifier = modifier.height(50.dp))

        // id
        Text(
            text =  stringResource(id = R.string.ID),
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
            label = { Text(stringResource(id = R.string.input_ID)) },
            placeholder = { Text(stringResource(id = R.string.ID_daisy)) },
            singleLine = true,
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

        TextField(
            value = userPw,
            onValueChange = { newValue ->
                userPw = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_PW)) },
            placeholder = { Text(stringResource(id = R.string.PW_TEXT)) },
            singleLine = true,
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

        TextField(
            value = userName,
            onValueChange = { newValue ->
                userName = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_NICKNAME)) },
            placeholder = { Text(stringResource(id = R.string.NICKNAME_TEXT)) },
            singleLine = true,
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

        TextField(
            value = userMbti,
            onValueChange = { newValue ->
                userMbti = newValue }, // 이 부분 안쓰면 입력안됨
            modifier = modifier
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.input_MBTI)) },
            placeholder = { Text(stringResource(id = R.string.MBTI_TEXT)) },
            singleLine = true,
        )

        // 2 가중치 만큼 빈공간 추가
        Spacer(modifier = modifier.weight(2f))

        // signup button
        val context = LocalContext.current
        AppButton(
            text = stringResource(id = R.string.SIGNUP),
            onClick = {
                val user = getUserInfo(userId, userPw, userName, userMbti)
                val validateSignUpData = signupViewModel.validateSignUp(user)
                if(validateSignUpData == null){
                    Toast.makeText(context, "회원가입성공", Toast.LENGTH_SHORT).show()
                    signupViewModel.signUp(user)
                    navController.navigate("login") {
                        // BackStack 모두 비우고 home 이동.
                        popUpTo("signup") { inclusive = true }
                    }
                }
            },
            padding = PaddingValues(vertical = 10.dp)
        )
    }
}

private fun getUserInfo(userId: String?, userPw: String?, userName: String?, userMbti: String?): UserData = UserData(
    id = userId.toString(),
    pw = userPw.toString(),
    name = userName.toString(),
    mbti = userMbti.toString(),
)

