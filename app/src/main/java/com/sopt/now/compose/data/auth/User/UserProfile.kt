package com.sopt.now.compose.data.auth.User

import androidx.compose.ui.graphics.vector.ImageVector

sealed class UserProfile {
    data class Friend(
        val profileImage: ImageVector,
        val name: String,
        val selfDescription: String,
    ) : UserProfile()

    data class MyProfile(
        val profileImage: ImageVector,
        val name: String,
        val music: String,
    ) : UserProfile()
}