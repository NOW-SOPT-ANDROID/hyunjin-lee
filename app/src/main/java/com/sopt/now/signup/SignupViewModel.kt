package com.sopt.now.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.data.UserData
import com.sopt.now.data.UserDatabase
import com.sopt.now.main.UserRepository
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository

    init {
        val userDao = UserDatabase.getUserDatabase(application).userDao()
        userRepository = UserRepository(userDao)
    }

    // 회원가입 함수
    fun signUp(user: UserData): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        // Coroutine을 사용하여 비동기 처리
        viewModelScope.launch {
            val insertResult = userRepository.insert(user)
            result.postValue(insertResult != -1L)
        }
        return result
    }

    // 회원가입 유효성 검사
    fun validateSignUp(user: UserData): Int? {
        return when {
            !isValidId(user.userid) -> R.string.isvalid_id
            !isValidPassword(user.userpw) -> R.string.isvalid_pw
            !isValidNickname(user.username) -> R.string.isvalid_name
            !isValidMbti(user.usermbti.uppercase()) -> R.string.isvalid_mbti
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
        private const val PREF_KEY = "user_data"
    }
}