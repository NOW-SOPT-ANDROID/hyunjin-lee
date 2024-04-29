package com.sopt.now.presentation.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.data.User.UserDao
import com.sopt.now.data.User.UserData
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.presentation.auth.login.LoginActivity

class SignUpActivity : AppCompatActivity()  {
    private val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clicksignUpButton()
    }

    private fun clicksignUpButton() {
        binding.btSignupButton.setOnClickListener {
            val user = getUserInfo()
            // 유효성 검사 후 회원가입 진행
            setSignupResult(user)
        }
    }

    private fun setSignupResult(user: UserData) {
        val userDao : UserDao? = null
        val validateSignUpData = viewModel.validateSignUp(user)
        if(validateSignUpData == null){
            viewModel.signUp(user).observe(this) { isSuccessful ->
                if (isSuccessful) {
                    // 회원가입 성공, 로그인 액티비티로 데이터 전달
                    Snackbar.make(binding.root, "회원가입 성공", Snackbar.LENGTH_SHORT).show()
                    saveSignUpInfo(user.userid, user.userpw) // 사용자 정보 저장
                    startLoginActivity()
                } else {
                    // 회원가입 실패, 사용자에게 메시지 표시
                    Snackbar.make(binding.root, "이미 존재하는 아이디입니다.", Snackbar.LENGTH_SHORT).show()
                }
            }
        } else{
            Snackbar.make(binding.root, this.getString(validateSignUpData), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun startLoginActivity() {
        val(userId, userPw) = Pair(
            binding.etSignupId.text.toString(),
            binding.etSignupPw.text.toString()
        )
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("userId", userId)
        intent.putExtra("userPw", userPw)
        startActivity(intent)
        finish()
    }

    // userinfo 입력
    private fun getUserInfo(): UserData = UserData(
        userprofileImage = R.drawable.ic_profile_img,
        userid = binding.etSignupId.text.toString(),
        userpw = binding.etSignupPw.text.toString(),
        username = binding.etSignupNickname.text.toString(),
        usermbti = binding.etSignupMbti.text.toString(),
    )

    // SharedPreferences를 사용하여 회원가입 정보 저장
    private fun saveSignUpInfo(userId: String, userPw: String) {
        val sharedPreferences = getSharedPreferences("userLoginInfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userId", userId)
        editor.putString("userPw", userPw)
        editor.apply()
    }
}