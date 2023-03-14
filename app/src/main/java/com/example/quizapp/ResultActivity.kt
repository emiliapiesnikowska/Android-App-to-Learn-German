package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    // setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0 )

        binding.resultLabel.text = getString(R.string.result_score,score)

        binding.tryAgainBtn.setOnClickListener {
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        }


    }
}