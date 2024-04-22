package com.sopt.now.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.UserData

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<Int?>()
    val signUpResult: LiveData<Int?> = _signUpResult

    private val _user = MutableLiveData<UserData?>()
    val user: LiveData<UserData?> = _user

    fun signUp(user: UserData) {
        val validationResult = validateSignUp(user)
        if (validationResult == null) {
            _user.value = user // 유효성 검사를 통과했다면, User 객체 저장
        }
        _signUpResult.value = validationResult
    }

    companion object {
        private const val NICKNAME_PATTERN = "^[a-zA-Z0-9]*$"
        private const val MBTI_PATTERN = "^(E|I)(S|N)(T|F)(J|P)$"
        private val nicknameRegex = Regex(NICKNAME_PATTERN)
        private val mbtiRegex = Regex(MBTI_PATTERN)
    }

    // 회원가입 유효성 검사
    private fun validateSignUp(user: UserData): Int? {
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
}