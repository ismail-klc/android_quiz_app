package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticDatas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionActivity extends AppCompatActivity {

    private int timer = 20;
    private Handler handler;
    private Runnable runnable;
    private ProgressBar progressBar;
    private TextView txtQuestion, txtQuestionNo, txtPoint;
    private Button btnA, btnB, btnC, btnD;
    private ArrayList<String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().hide();
        initVariables();
        initQuestion();
        initTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    public void handleChooseOption(View view) {
        handler.removeCallbacks(runnable);

        Button b = (Button)view;
        String buttonText = b.getText().toString();

        int position = StaticDatas.currentQuestion - 1;
        if (buttonText.equals(StaticDatas.questions.get(position).getCorrectAnswer())){
            b.setBackgroundResource(R.drawable.btn_background_success);
            StaticDatas.point++;
        }
        else {
            b.setBackgroundResource(R.drawable.btn_background_wrong);
        }

        handleNextQuestion();
    }

    private void handleNextQuestion(){
        StaticDatas.currentQuestion++;
        Intent intent;

        if (StaticDatas.currentQuestion - 1 < StaticDatas.numberOfQuestions){
            intent = new Intent(QuestionActivity.this, QuestionActivity.class);
        }
        else {
            intent = new Intent(QuestionActivity.this, ResultActivity.class);
            StaticDatas.currentQuestion = 1;
        }

        startActivity(intent);
        finish();
    }

    private void initQuestion(){
        int position = StaticDatas.currentQuestion - 1;
        answers.clear();
        answers = (ArrayList<String>) StaticDatas.questions.get(position).getIncorrectAnswers().clone();
        answers.add(StaticDatas.questions.get(position).getCorrectAnswer());
        Collections.shuffle(answers);

        txtPoint.setText(String.valueOf(StaticDatas.point));
        txtQuestion.setText(Html.fromHtml(StaticDatas.questions.get(position).getQuestion()));;
        btnA.setText(answers.get(0));
        btnB.setText(answers.get(1));
        btnC.setText(answers.get(2));
        btnD.setText(answers.get(3));

        txtQuestionNo.setText(StaticDatas.currentQuestion + "/" + StaticDatas.numberOfQuestions);
    }

    private void initTimer(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (timer == 0){
                    handler.removeCallbacks(runnable);
                    handleNextQuestion();
                }
                progressBar.setProgress(progressBar.getProgress() - 5);
                timer--;
                handler.postDelayed(runnable,1000);
            }
        };
        handler.post(runnable);
    }

    private void initVariables(){
        progressBar = findViewById(R.id.progressBar);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionNo = findViewById(R.id.txtQuestionNo);
        txtPoint = findViewById(R.id.txtPoint);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        answers = new ArrayList<>();
    }
}
