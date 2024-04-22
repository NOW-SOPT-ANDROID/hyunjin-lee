package com.sopt.now.compose.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.now.compose.ui.HomeScreen.HomeScreen
import com.sopt.now.compose.ui.LoginScreen.LoginScreen
import com.sopt.now.compose.ui.LoginScreen.LoginViewModel
import com.sopt.now.compose.ui.MypageScreen.MyPageViewModel
import com.sopt.now.compose.ui.MypageScreen.MypageScreen
import com.sopt.now.compose.ui.SignupScreen.SignupScreen
import com.sopt.now.compose.ui.SignupScreen.SignUpViewModel

@Composable
fun Navigation(navController: NavHostController) {
    val signupViewModel: SignUpViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
    val loginViewModel: LoginViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
    val myPageViewModel: MyPageViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)

    NavHost(navController = navController, startDestination = "login") {
        composable("home") {
            HomeScreen()
        }
        composable("mypage") {
            MypageScreen(myPageViewModel = myPageViewModel, navController = navController)
        }
        composable("login") {
            // LoginScreen에서는 UserViewModel을 사용하여 로그인 성공 후 사용자 정보를 저장
            LoginScreen(loginViewModel = loginViewModel, navController = navController)
        }
        composable("signup") {
            SignupScreen(navController = navController, signupViewModel = signupViewModel)
        }
    }
}