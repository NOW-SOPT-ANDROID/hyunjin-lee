package com.sopt.now.compose.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sopt.now.compose.MainViewModel
import com.sopt.now.compose.ui.HomeScreen.FriendViewModel
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
    val mainViewModel: MainViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
    val friendViewModel: FriendViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)

    NavHost(navController = navController, startDestination = "login/{userId}") {
        composable("home") {
            HomeScreen(friendViewModel)
        }
        composable("mypage") {
            MypageScreen(myPageViewModel = myPageViewModel, navController = navController)
        }
        composable(
            "login/{memberId}",
            arguments = listOf(navArgument("memberId") { type = NavType.StringType })
        ) { backStackEntry ->
            // `LoginScreen`에 `userId` 전달
            LoginScreen(
                mainViewModel = mainViewModel,
                loginViewModel = loginViewModel,
                navController = navController,
                navBackStackEntry = backStackEntry
            )
        }
        composable("signup") {
            SignupScreen(navController = navController, signupViewModel = signupViewModel)
        }
    }
}