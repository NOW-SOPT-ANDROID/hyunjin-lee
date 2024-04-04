package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        // 인텐트에서 데이터 추출
        val getId = intent.getStringExtra("id")
        val getPw = intent.getStringExtra("pw")
        val getName = intent.getStringExtra("name")
        val getMbti = intent.getStringExtra("mbti")
        Log.d("login","${getId}, ${getPw}, ${getName}, ${getMbti}")

        // EditText에 데이터 설정
        binding.etLoginId.setText(getId) // setText 메소드 사용
        binding.etLoginPw.setText(getPw) // setText 메소드 사용

        setContentView(binding.root)

        binding.btLoginButton.setOnClickListener {
            if(binding.etLoginId.text.toString() == getId && binding.etLoginPw.text.toString() == getPw){
                // Intent를 활용하여 Activity 새로 열기
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("id", getId)
                    putExtra("pw", getPw)
                    putExtra("name", getName)
                    putExtra("mbti", getMbti)
                }
                startActivity(intent)
            }
        }

        binding.tvLoginSignupButton.setOnClickListener {
            // Intent를 활용하여 Activity 새로 열기
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}