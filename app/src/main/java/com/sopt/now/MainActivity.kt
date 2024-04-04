package com.sopt.now

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 인텐트에서 데이터 추출
        val getId = intent.getStringExtra("id")
        val getPw = intent.getStringExtra("pw")
        val getName = intent.getStringExtra("name")
        val getMbti = intent.getStringExtra("mbti")
        Log.d("login","${getId}, ${getPw}, ${getName}, ${getMbti}")

        // EditText에 데이터 설정
        binding.tvMainIdContent.setText(getId) // setText 메소드 사용
        binding.tvMainPwContent.setText(getPw) // setText 메소드 사용
        binding.tvMainMbtiContent.setText(getMbti) // setText 메소드 사용
        binding.tvMainName.setText(getName) // setText 메소드 사용

        setContentView(binding.root)
    }
}