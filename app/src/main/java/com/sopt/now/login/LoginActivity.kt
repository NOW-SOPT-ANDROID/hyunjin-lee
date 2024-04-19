package com.sopt.now.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.MyApplication
import com.sopt.now.PreferenceUtil
import com.sopt.now.R
import com.sopt.now.signup.SignUpActivity
import com.sopt.now.data.UserData
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUserData()

        // 자동 로그인
        loginObserver()

        // 로그인 버튼 클릭 시
        binding.btLoginButton.setOnClickListener {
            handleLoginClick()
        }
        
        // 회원가입 버튼 클릭 시
        binding.tvLoginSignupButton.setOnClickListener {
            handleSignupClick()
        }
    }

    override fun onResume() {
        super.onResume()
        setUserData() // 사용자 데이터를 다시 설정
    }

    private fun setUserData() {
        val userData = viewModel.getUserInfo()
        Log.d("login", "$userData")
        binding.etLoginId.setText(userData.id) // 로그인 화면에 사용자 ID를 자동으로 작성
        binding.etLoginPw.setText(userData.pw) // 로그인 화면에 사용자 PW를 자동으로 작성
    }

    private fun handleLoginClick() {
        val(inputId, inputPw) = Pair(
            binding.etLoginId.text.toString(),
            binding.etLoginPw.text.toString()
        )

        val userInfo = viewModel.getUserInfo()
        if (viewModel.loginValid(inputId, inputPw, userInfo)) {
            Snackbar.make(binding.root, "로그인 성공", Snackbar.LENGTH_SHORT).show()
            navigateToMain()
        } else {
            Snackbar.make(binding.root, "로그인 실패", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun handleSignupClick() {
        Intent(this@LoginActivity, SignUpActivity::class.java).let {
            startActivity(it)
        }
    }

    private fun loginObserver() {
        viewModel.userInfo.observe(this) {
            if (viewModel.userInfo.value == true) {
                Snackbar.make(binding.root, "로그인 성공", Snackbar.LENGTH_SHORT).show()
                navigateToMain()
            }
        }
    }

    private fun navigateToMain() {
        Intent(this, MainActivity::class.java).let {
            startActivity(it)
        }
        finish()
    }
}