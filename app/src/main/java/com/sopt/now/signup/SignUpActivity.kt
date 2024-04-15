package com.sopt.now.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.data.UserData
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.login.LoginActivity

class SignUpActivity : AppCompatActivity()  {
    private val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }
    private val viewModel: SignUpViewModel by viewModels()
    companion object {
        const val USER_DATA = "user_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSignupResult()
        // 클릭리스너 설정
        binding.btSignupButton.setOnClickListener {
            val user = getUserInfo()
            viewModel.signUp(user)
        }

        setContentView(binding.root)
    }

    private fun setSignupResult() {
        viewModel.signUpResult.observe(this) { validationResult ->
            if (validationResult == null) {
                viewModel.user.value?.let { user ->
                    Snackbar.make(binding.root, "회원가입 성공", Snackbar.LENGTH_SHORT).show()
                    val resultIntent = Intent().apply {
                        putExtra(USER_DATA, user) // User 객체를 Intent에 포함
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            } else {
                Snackbar.make(binding.root, this.getString(validationResult), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // userinfo 입력
    private fun getUserInfo(): UserData = UserData(
        profileImage = R.drawable.ic_profile_img,
        id = binding.etSignupId.text.toString(),
        pw = binding.etSignupPw.text.toString(),
        name = binding.etSignupNickname.text.toString(),
        mbti = binding.etSignupMbti.text.toString(),
    )
}