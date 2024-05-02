package com.sopt.now.compose.ui.MainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.MainScaffold
import com.sopt.now.compose.Navigation.Navigation
import com.sopt.now.compose.ui.LoginScreen.LoginViewModel

@Composable
fun MainScreen(loginViewModel: LoginViewModel) {
    val isUserInfoAvailable by loginViewModel.loginstate.collectAsState(initial = false)
    val navController = rememberNavController()

    // LaunchedEffect를 사용하여 최초 한 번만 자동 로그인 확인
    LaunchedEffect(key1 = true) {
        if (isUserInfoAvailable as Boolean) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    MainScaffold(navController = navController) {
        Navigation(navController = navController)
    }
}