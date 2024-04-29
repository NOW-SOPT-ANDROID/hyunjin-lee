package com.sopt.now.presentation.auth.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.data.auth.SignUpData.RequestSignUpDto
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.presentation.auth.login.LoginActivity

class SignUpActivity : AppCompatActivity()  {
    private val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        initObserver()
    }

    private fun initViews() {
        binding.btSignupButton.setOnClickListener {
            viewModel.signUp(getSignUpRequestDto())
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { signUpState ->
            Toast.makeText(
                this@SignUpActivity,
                signUpState.message,
                Toast.LENGTH_SHORT,
            ).show()
            if (signUpState.isSuccess) {
                // 회원가입 성공 시 LoginActivity로 이동
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java).apply {
                    // userId가 null이 아닌 경우에만 putExtra 호출
                    signUpState.userId?.let { userId ->
                        putExtra("userId", userId)
                    }
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.etSignupId.text.toString()
        val password = binding.etSignupPw.text.toString()
        val nickname = binding.etSignupName.text.toString()
        val phoneNumber = binding.etSignupMbti.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }
}