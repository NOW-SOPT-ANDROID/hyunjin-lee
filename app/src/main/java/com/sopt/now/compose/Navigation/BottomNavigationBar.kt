package com.sopt.now.compose.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.data.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    ) {
    val items = listOf(
        BottomNavigationItem(
            icon = Icons.Filled.Home,
            route = "home",
            label = stringResource(id = R.string.Home)
        ),
        BottomNavigationItem(
            icon = Icons.Filled.Person,
            route = "mypage",
            label = stringResource(id = R.string.Mypage)
        )
    )
    NavigationBar {
        val currentRoute = navController.currentDestination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}