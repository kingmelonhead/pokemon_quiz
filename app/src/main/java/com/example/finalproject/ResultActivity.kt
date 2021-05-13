package com.example.finalproject

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private lateinit var viewModel: ResultViewModel
    lateinit var mediaPlayer : MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        mediaPlayer =  MediaPlayer.create(this, R.raw.btn_sound)

        viewModel.answers = intent.getSerializableExtra("Data") as Array<ResultContainer>

        backBtn.setOnClickListener(restartGame)

        val questionsArray = viewModel.questions
        score.text = intent.getIntExtra("Score", 0).toString()
        val recyclerView = findViewById<RecyclerView>(R.id.question_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            adapter?.notifyDataSetChanged()
            adapter = AnswerAdapter(viewModel.questions, viewModel.answers)}

    }
    private val restartGame = View.OnClickListener {
        mediaPlayer.start()
        restart()
    }

    private fun restart(){
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}