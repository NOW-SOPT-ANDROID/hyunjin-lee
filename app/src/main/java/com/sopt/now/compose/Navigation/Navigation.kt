package com.sopt.now.compose.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.now.compose.MainScaffold
import com.sopt.now.compose.ui.HomeScreen.HomeScreen
import com.sopt.now.compose.ui.LoginScreen.LoginScreen
import com.sopt.now.compose.ui.MypageScreen.MypageScreen
import com.sopt.now.compose.ui.SignupScreen.SignupScreen
import com.sopt.now.compose.ui.SignupScreen.UserViewModel

@Composable
fun Navigation(navController: NavHostController) {

    val userViewModel: UserViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)

    NavHost(navController = navController, startDestination = "login") {
        composable("home") {
            MainScaffold(navController = navController) {
                HomeScreen()
            }
        }
        composable("mypage") {
            MainScaffold(navController = navController) {
                // UserViewModel에서 사용자 정보를 가져와서 MyPageScreen에 제공
                MypageScreen(navController = navController, userViewModel = userViewModel)
            }
        }
        composable("login") {
            // LoginScreen에서는 UserViewModel을 사용하여 로그인 성공 후 사용자 정보를 저장
            LoginScreen(userViewModel = userViewModel, navController = navController)
        }
        composable("signup") {
            SignupScreen(navController = navController, userViewModel = userViewModel)
        }
    }
}
