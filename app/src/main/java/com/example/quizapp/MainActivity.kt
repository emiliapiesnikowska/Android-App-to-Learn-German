package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswrCount = 0
    private var quizCount = 1
    private var QUIZ_COUNT = 5

    private val quizData = mutableListOf(
        mutableListOf("Wie viel kostet das?", "Ile to kosztuje?", "Gdzie mogę znaleźć...?","Co chcesz pić?", "Potrzebuję..."),
        mutableListOf("Wie alt bist du?", "Ile masz lat?","Jakie masz hobby?","Jakie są twoje ulubione miejsca do odwiedzenia?", "Co mówią prognozy pogody na tę najbliższą tydzień?"),
        mutableListOf("Wo kann ich ... finden?", "Gdzie mogę znaleźć...?", "Jakie są twoje cele zawodowe?","Jakie masz hobby?", "Ile masz lat?"),
        mutableListOf("Ich habe kein Geld.", " Nie mam pieniędzy", "Potrzebuję...","Gdzie mogę znaleźć...?", "Jakie masz hobby?"),
        mutableListOf("Was ist \"Dobry wieczór\" auf Deutsch?", "Guten Abend", "Guten Tag","Guten Morgen", "Tschüss")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showNextQuiz()
    }

    fun showNextQuiz(){
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        val quiz = quizData[0]

        binding.questionLabel.text = quiz[0]
        rightAnswer = quiz[1]

        quiz.removeAt(0)
        quiz.shuffle()

        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]
        quizData.removeAt(0)
    }

    fun checkAnswer(view: View){
        val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle: String
        if (btnText == rightAnswer){
            alertTitle="Dobrze!"
            rightAnswrCount++
        }
        else {
            alertTitle = "Źle..."
        }

        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Odpowiedź: $rightAnswer")
            .setPositiveButton("OK"){
                    dialogInterface,i -> checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }
    fun checkQuizCount(){
        if( quizCount == QUIZ_COUNT){
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswrCount)
            startActivity(intent)
        }
        else{
            quizCount++
            showNextQuiz()
        }
    }
}