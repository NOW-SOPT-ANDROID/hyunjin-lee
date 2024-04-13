package com.sopt.now.compose.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.Navigation.Navigation

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Navigation(navController = navController)
}