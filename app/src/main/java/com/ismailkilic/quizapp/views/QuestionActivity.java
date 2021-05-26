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
    private Intent intent;


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

        Button btn = (Button) view;
        String buttonText = btn.getText().toString();

        int position = StaticDatas.currentQuestion - 1;
        String answer = StaticDatas.questions.get(position).getCorrectAnswer();

        if (buttonText.equals(answer)) {
            btn.setBackgroundResource(R.drawable.btn_background_success);
            handleIncrementPoint();
        } else {
            btn.setBackgroundResource(R.drawable.btn_background_wrong);
            findCorrectAnswer(answer);
        }

        runnable = new Runnable() {
            public void run() {
                handleNextQuestion();
            }
        };
        handler.postDelayed(runnable,1000);
    }

    private void handleIncrementPoint() {
        if (StaticDatas.mode == StaticDatas.Mode.MULTI){
            if (StaticDatas.turn == StaticDatas.Turn.USER1){
                StaticDatas.user1Point++;
            }
            else {
                StaticDatas.user2Point++;
            }
        }
        else {
            StaticDatas.point++;
        }
    }

    private void findCorrectAnswer(String answer) {
        if (btnA.getText().toString().equals(answer)) {
            btnA.setBackgroundResource(R.drawable.btn_background_success);
        } else if (btnB.getText().toString().equals(answer)) {
            btnB.setBackgroundResource(R.drawable.btn_background_success);
        } else if (btnC.getText().toString().equals(answer)) {
            btnC.setBackgroundResource(R.drawable.btn_background_success);
        } else if (btnD.getText().toString().equals(answer)) {
            btnD.setBackgroundResource(R.drawable.btn_background_success);
        }
    }

    private void handleNextQuestion() {
        StaticDatas.currentQuestion++;

        if (StaticDatas.currentQuestion - 1 < StaticDatas.numberOfQuestions) {
            intent = new Intent(QuestionActivity.this, QuestionActivity.class);
        } else {
            handleFinishQuestions();
        }

        handler.removeCallbacks(runnable);
        startActivity(intent);
        finish();
    }

    private void handleFinishQuestions(){
        if (StaticDatas.mode == StaticDatas.Mode.MULTI){
            if (StaticDatas.turn == StaticDatas.Turn.USER1){
                intent = new Intent(QuestionActivity.this, MultiHoldActivity.class);
                StaticDatas.turn = StaticDatas.Turn.USER2;
            }
            else {
                intent = new Intent(QuestionActivity.this, ResultActivity.class);
            }
        }
        else if (StaticDatas.mode == StaticDatas.Mode.NORMAL){
            intent = new Intent(QuestionActivity.this, ResultActivity.class);
        }
    }

    private void initQuestion() {
        int position = StaticDatas.currentQuestion - 1;
        answers.clear();
        answers = (ArrayList<String>) StaticDatas.questions.get(position).getIncorrectAnswers().clone();
        answers.add(StaticDatas.questions.get(position).getCorrectAnswer());
        Collections.shuffle(answers);

        initPoints();
        txtQuestion.setText(Html.fromHtml(StaticDatas.questions.get(position).getQuestion()));

        btnA.setText(Html.fromHtml(answers.get(0)));
        btnB.setText(Html.fromHtml(answers.get(1)));
        btnC.setText(Html.fromHtml(answers.get(2)));
        btnD.setText(Html.fromHtml(answers.get(3)));

        txtQuestionNo.setText(StaticDatas.currentQuestion + "/" + StaticDatas.numberOfQuestions);
    }

    private void initPoints() {
        if (StaticDatas.mode == StaticDatas.Mode.MULTI){
            if (StaticDatas.turn == StaticDatas.Turn.USER1){
                txtPoint.setText(String.valueOf(StaticDatas.user1Point));
            }
            else {
                txtPoint.setText(String.valueOf(StaticDatas.user2Point));
            }
        }
        else {
            txtPoint.setText(String.valueOf(StaticDatas.point));
        }

    }

    private void initTimer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (timer == 0) {
                    handler.removeCallbacks(runnable);
                    handleNextQuestion();
                }
                progressBar.setProgress(progressBar.getProgress() - 5);
                timer--;
                handler.postDelayed(runnable, 1000);
            }
        };
        handler.post(runnable);
    }

    private void initVariables() {
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
