package com.sopt.now.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.PreferenceUtil
import com.sopt.now.signup.SignUpActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUserData() // user data 세팅
        handleLoginClick() // 로그인 버튼 클릭 시
        handleSignupClick() // 회원가입 버튼 클릭 시
    }

    private fun setUserData() {
        val preferenceUtil = PreferenceUtil(applicationContext)
        val userData = preferenceUtil.getUserData(PreferenceUtil.PREF_KEY) // PREF_KEY는 사용자 데이터를 저장할 때 사용한 키 값
        binding.etLoginId.setText(userData.userid)
        binding.etLoginPw.setText(userData.userpw)

        // 회원가입 때 입력한 ID와 PW를 받아와서 EditText에 설정
        intent.getStringExtra("userId")?.let {
            binding.etLoginId.setText(it)
        }
        intent.getStringExtra("userPw")?.let {
            binding.etLoginPw.setText(it)
        }
    }

    private fun observeLoginResult() {
        viewModel.loginResult.observe(this) { isSuccess ->
            if (isSuccess) {
                Snackbar.make(binding.root, "로그인 성공!", Snackbar.LENGTH_SHORT).show()
                navigateToMain()
            } else {
                Snackbar.make(binding.root, "로그인 실패. 아이디 혹은 비밀번호를 확인해주세요.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleLoginClick() {
        binding.btLoginButton.setOnClickListener {
            val(inputId, inputPw) = Pair(
                binding.etLoginId.text.toString(),
                binding.etLoginPw.text.toString()
            )
            viewModel.login(inputId, inputPw)
            observeLoginResult()
        }
    }

    private fun handleSignupClick() {
        binding.tvLoginSignupButton.setOnClickListener {
            val signUpIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
            signUpActivityResult.launch(signUpIntent) // 변경된 부분
        }
     }

    // registerForActivityResult API 사용
    private val signUpActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                val userId = it.getStringExtra("userId")
                val userPw = it.getStringExtra("userPw")
                binding.etLoginId.setText(userId)
                binding.etLoginPw.setText(userPw)
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