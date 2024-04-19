package com.sopt.now.compose.ui.MypageScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MyPageViewModel(private val state: SavedStateHandle) : ViewModel() {
    var userId: String?
        get() = state["userId"]
        set(value) { state["userId"] = value }
}