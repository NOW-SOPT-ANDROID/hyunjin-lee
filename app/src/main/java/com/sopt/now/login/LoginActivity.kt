package com.sopt.now.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

        setUserData()

        // 여기에 추가
        viewModel.logAllUserInfo()

        // 로그인 버튼 클릭 시
        binding.btLoginButton.setOnClickListener {
            handleLoginClick()
        }

        // 회원가입 버튼 클릭 시
        binding.tvLoginSignupButton.setOnClickListener {
            handleSignupClick()

        }
    }

    private fun setUserData() {
        val preferenceUtil = PreferenceUtil(applicationContext)
        val userData = preferenceUtil.getUserData(PreferenceUtil.PREF_KEY) // PREF_KEY는 사용자 데이터를 저장할 때 사용한 키 값
        binding.etLoginId.setText(userData.userid)
        binding.etLoginPw.setText(userData.userpw)
    }


    private fun handleLoginClick() {
        val(inputId, inputPw) = Pair(
            binding.etLoginId.text.toString(),
            binding.etLoginPw.text.toString()
        )

        // LiveData 관찰
        viewModel.userInfo.observe(this) { userData ->
            if (userData != null && inputId == userData.userid && inputPw == userData.userpw) {
                Snackbar.make(binding.root, "로그인 성공!", Snackbar.LENGTH_SHORT).show()
                navigateToMain()
            } else {
                Snackbar.make(binding.root, "로그인 실패. 아이디 혹은 비밀번호를 확인해주세요.", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.getUserInfo(inputId)
    }

    private fun handleSignupClick() {
        val signUpIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivityForResult(signUpIntent, SIGN_UP_REQUEST_CODE)
    }

    // LoginActivity 내에 onActivityResult() 추가
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_UP_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
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

    companion object {
        const val SIGN_UP_REQUEST_CODE = 1 // 회원가입 요청 코드 정의
    }
}