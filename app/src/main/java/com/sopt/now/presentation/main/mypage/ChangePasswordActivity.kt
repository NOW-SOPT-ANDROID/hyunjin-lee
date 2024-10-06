package com.sopt.now.presentation.main.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityChangePwBinding
import com.sopt.now.presentation.auth.login.LoginActivity

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePwBinding
    private val viewModel: MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val memberId = sharedPreferences.getString(MEMBER_ID, "") ?: ""

        initViews(memberId)
        observeViewModel()
    }

    private fun initViews(memberId: String) {
        binding.btMypageChangePassword.setOnClickListener {
            val currentPassword = binding.etChangepwPre.text.toString()
            val newPassword = binding.etChangepwNew.text.toString()
            val newPasswordVerification = binding.etChangepwNewVerify.text.toString()

            viewModel.patchUserPassword(
                memberId,
                currentPassword,
                newPassword,
                newPasswordVerification
            )
        }
    }

    private fun observeViewModel() {
        viewModel.newpassword.observe(this) { passwordState ->
            Toast.makeText(this, passwordState.message, Toast.LENGTH_SHORT).show()
            if (passwordState.isSuccess) {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }

    companion object {
        private const val APP_PREFERENCES = "MyAppPreferences"
        private const val MEMBER_ID = "memberId"
    }
}