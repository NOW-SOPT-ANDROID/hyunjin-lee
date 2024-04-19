package com.sopt.now.compose.ui.MainScreen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.MainScaffold
import com.sopt.now.compose.Navigation.Navigation

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    MainScaffold(navController = navController) {
        Navigation(navController = navController)
    }
}