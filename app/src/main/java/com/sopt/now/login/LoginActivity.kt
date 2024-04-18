package com.sopt.now.login

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.CheckBox
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.signup.SignUpActivity
import com.sopt.now.data.UserData
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    // 상수화
    companion object {
        const val USER_DATA = "user_data"
        const val USER_ID = "user_id"
        const val USER_PW = "user_pw"
        const val USER_NAME = "user_name"
        const val USER_MBTI = "user_mbti"
        const val IS_AUTO_LOGIN = "is_auto_login"
    }

    // 회원가입 정보 받는 콜백
    private val getSignupResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            if (result.resultCode == RESULT_OK) {
                // 회원가입 성공 후 받은 사용자 정보를 SharedPreferences를 통해 저장
                val user: UserData? = result.data?.getParcelableExtra(USER_DATA)
                user?.let {
                    with(sharedPreferences.edit()) {
                        putString(USER_ID, it.id)
                        putString(USER_PW, it.pw)
                        putString(USER_NAME, it.name)
                        putString(USER_MBTI, it.mbti)
                        apply()
                    }
                    viewModel.setUserInfo(it)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        setUserData()// EditText에 데이터 설정
        checkAutoLogin()

        // setOnClickListener 설정
        binding.btLoginButton.setOnClickListener {
            handleLoginClick()
        }
        binding.tvLoginSignupButton.setOnClickListener {
            handleSignupClick()
        }
    }

    private fun setUserData() {
        viewModel.userInfo.observe(this) { user ->
            binding.etLoginId.setText(user.id) // 로그인 화면에 사용자 ID를 자동으로 작성
            binding.etLoginPw.setText(user.pw) // 로그인 화면에 사용자 PW를 자동으로 작성
        }
    }

    private fun handleLoginClick() {
        val(inputId, inputPw) = Pair(
            binding.etLoginId.text.toString(),
            binding.etLoginPw.text.toString()
        )

        if (viewModel.loginValid(inputId, inputPw)) {
            with(sharedPreferences.edit()) {
                putString(USER_ID, inputId)
                putString(USER_PW, inputPw)
                putBoolean(IS_AUTO_LOGIN, true)
                apply()
            }

            Snackbar.make(binding.root, "로그인 성공", Snackbar.LENGTH_SHORT).show()
            navigateToMain()
        } else {
            Snackbar.make(binding.root, "로그인 실패", Snackbar.LENGTH_SHORT).show()
        }
        finish()
    }

    private fun handleSignupClick() {
        getSignupResult.launch(Intent(this@LoginActivity, SignUpActivity::class.java))
    }

    private fun checkAutoLogin() {
        val userId = sharedPreferences.getString(USER_ID, null)
        val userPw = sharedPreferences.getString(USER_PW, null)
        val userName = sharedPreferences.getString(USER_NAME, null)
        val userMbti = sharedPreferences.getString(USER_MBTI, null)
        val isAutoLogin = sharedPreferences.getBoolean(IS_AUTO_LOGIN, false)
        Log.d("login", "$isAutoLogin")
        if (userId != null && userPw != null && isAutoLogin) {
            // 자동로그인 처리
            val user = UserData(userId, userPw, userName.toString(), userMbti.toString(), 0)
            viewModel.setUserInfo(user) // ViewModel 업데이트
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
            putExtra(USER_DATA, viewModel.userInfo.value)
        }
        startActivity(intent)
        finish()
    }
}