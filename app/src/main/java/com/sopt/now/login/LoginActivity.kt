package com.sopt.now.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.signup.SignUpActivity
import com.sopt.now.data.UserData
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()

    // 회원가입 정보
    private val getSignupResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val user: UserData? = result.data?.getParcelableExtra(USER_DATA)
                user?.let { viewModel.setUserInfo(it) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUserData()// EditText에 데이터 설정

        // setOnClickListener 설정
        binding.btLoginButton.setOnClickListener {
            handleLoginClick()
        }
        binding.tvLoginSignupButton.setOnClickListener {
            handleSignupClick()
        }

        setContentView(binding.root)
    }

    private fun setUserData() {
        viewModel.userInfo.observe(this) { user ->
            binding.etLoginId.setText(user.id) // 로그인 화면에 사용자 ID를 자동으로 작성
            binding.etLoginPw.setText(user.pw) // 로그인 화면에 사용자 PW를 자동으로 작성
        }
    }

    // 상수화
    companion object {
        const val USER_DATA = "user_data"
    }

    private fun handleLoginClick() {
        val inputId = binding.etLoginId.text.toString()
        val inputPwd = binding.etLoginPw.text.toString()

        if (viewModel.loginValid(inputId, inputPwd)) {
            Snackbar.make(binding.root, "로그인 성공", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                putExtra(USER_DATA, viewModel.userInfo.value)
            }
            startActivity(intent)
            finish()
        } else {
            Snackbar.make(binding.root, "로그인 실패", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun handleSignupClick() {
        getSignupResult.launch(Intent(this@LoginActivity, SignUpActivity::class.java))
    }
}