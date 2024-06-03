package com.sopt.now.compose.ui.MainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.MainScaffold
import com.sopt.now.compose.Navigation.Navigation
import com.sopt.now.compose.data.auth.LoginData.LoginState
import com.sopt.now.compose.ui.LoginScreen.LoginViewModel

@Composable
fun MainScreen(loginViewModel: LoginViewModel) {
    val loginState by loginViewModel.loginLiveData.observeAsState(initial = LoginState(false, ""))
    val navController = rememberNavController()

    // LaunchedEffect를 사용하여 최초 한 번만 자동 로그인 확인
    LaunchedEffect(key1 = true) {
        if (loginState.isSuccess) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    MainScaffold(navController = navController) {
        Navigation(navController = navController)
    }
}