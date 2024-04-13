package com.sopt.now.compose.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MyPageViewModel(private val state: SavedStateHandle) : ViewModel() {
    // 사용자 ID를 SavedStateHandle에서 관리
    var userId: String?
        get() = state["userId"]
        set(value) { state["userId"] = value }
}