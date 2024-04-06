package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity()  {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSignupButton.setOnClickListener {
            if (binding.etSignupId.text.length >= 6 && binding.etSignupId.text.length <= 10) {
                if (binding.etSignupPw.text.length >= 8 && binding.etSignupPw.text.length <= 12) {
                    if (binding.etSignupNickname.text.length >= 1 && !binding.etSignupNickname.text.contentEquals(
                            " "
                        )
                    ) {
                        // Intent를 활용하여 Activity 새로 열기
                        val intent = Intent(this, LoginActivity::class.java).apply {
                            putExtra("id", binding.etSignupId.text.toString())
                            putExtra("pw", binding.etSignupPw.text.toString())
                            putExtra("name", binding.etSignupNickname.text.toString())
                            putExtra("mbti", binding.etSignupMbti.text.toString())
                        }
                        startActivity(intent)
                    } else {
                        Snackbar.make(
                            binding.root,
                            "닉네임이 잘못되었습니다.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Snackbar.make(
                        binding.root,
                        "패스워드가 잘못되었습니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }else{
                Snackbar.make(
                    binding.root,
                    "ID가 잘못되었습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}