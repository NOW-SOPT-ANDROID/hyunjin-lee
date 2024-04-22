package com.sopt.now.signup

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.MyApplication
import com.sopt.now.R
import com.sopt.now.data.UserData

class SignUpViewModel : ViewModel() {
    fun signUp(user: UserData) {
        val json = Gson().toJson(user)
        MyApplication.prefs.setString(PREF_KEY, json)
    }

    // 회원가입 유효성 검사
    fun validateSignUp(user: UserData): Int? {
        return when {
            !isValidId(user.id) -> R.string.isvalid_id
            !isValidPassword(user.pw) -> R.string.isvalid_pw
            !isValidNickname(user.name) -> R.string.isvalid_name
            !isValidMbti(user.mbti.uppercase()) -> R.string.isvalid_mbti
            else -> null // 통과
        }
    }

    fun isValidId(id: String): Boolean = id.length in 6..10
    fun isValidPassword(pwd: String): Boolean = pwd.length in 8..12
    fun isValidNickname(nickname: String): Boolean = nickname.isNotBlank() && nickname.matches(nicknameRegex)
    fun isValidMbti(mbti: String): Boolean = mbti.isNotBlank() && mbti.matches(mbtiRegex)

    companion object {
        private const val NICKNAME_PATTERN = "^[a-zA-Z0-9]*$"
        private const val MBTI_PATTERN = "^(E|I)(S|N)(T|F)(J|P)$"
        private val nicknameRegex = Regex(NICKNAME_PATTERN)
        private val mbtiRegex = Regex(MBTI_PATTERN)
        private const val PREF_KEY = "userData"
    }
}