package com.sopt.now.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.data.auth.LoginData.RequestLoginDto
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        initObserver()
        setUserData() // user data 세팅
    }

    private fun initViews() {
        // 로그인 버튼 클릭시
        binding.btLoginButton.setOnClickListener {
            viewModel.login(getLoginRequestDto())
        }

        // 회원가입 버튼 클릭시
        binding.tvLoginSignupButton.setOnClickListener {
            Intent(this@LoginActivity, SignUpActivity::class.java).let {
                startActivity(it)
            }
        }
    }

    private fun initObserver() {
        viewModel.login_liveData.observe(this) { loginState ->
            Toast.makeText(
                this@LoginActivity,
                loginState.message,
                Toast.LENGTH_SHORT,
            ).show()
        }
        viewModel.login_liveData.observe(this) { loginState ->
            if (loginState.isSuccess) {
                navigateToMain()
            }
        }
    }

    private fun setUserData() {
        val memberId = intent.getStringExtra("userId")?.toIntOrNull() ?: 0
        viewModel.getUserInfo(memberId)
        observeLoginState()
    }

    // loginState에서 authenticationId를 받아와서 EditText에 설정
    private fun observeLoginState() {
        viewModel.user_liveData.observe(this) { userState ->
            if (userState.isSuccess) {
                binding.etLoginId.setText(userState.userId)
                // 회원 조회 성공 메시지 표시
                Toast.makeText(this, userState.message, Toast.LENGTH_SHORT).show()
            } else {
                // 실패 메시지 표시
                Toast.makeText(this, userState.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMain() {
        viewModel.login_liveData.value?.memberId?.let { memberId ->
            val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                putExtra("memberId", memberId)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun getLoginRequestDto(): RequestLoginDto {
        val id = binding.etLoginId.text.toString()
        val password = binding.etLoginPw.text.toString()
        return RequestLoginDto(
            authenticationId = id,
            password = password,
        )
    }
}