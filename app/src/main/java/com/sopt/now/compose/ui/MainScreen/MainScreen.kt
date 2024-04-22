package com.sopt.now.compose.ui.MainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.MainScaffold
import com.sopt.now.compose.Navigation.Navigation
import com.sopt.now.compose.ui.LoginScreen.LoginViewModel

@Composable
fun MainScreen(loginViewModel: LoginViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current // LifecycleOwner를 얻습니다.
    val navController = rememberNavController()

    // LaunchedEffect를 사용하여 최초 한 번만 자동 로그인 확인
    LaunchedEffect(key1 = true) {
        loginViewModel.userInfo.observe(lifecycleOwner) {
            if (loginViewModel.userInfo.value == true) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
    }

    MainScaffold(navController = navController) {
        Navigation(navController = navController)
    }
}