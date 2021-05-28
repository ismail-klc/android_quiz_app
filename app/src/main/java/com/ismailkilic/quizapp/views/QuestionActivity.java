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

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticData;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionActivity extends AppCompatActivity {

    private int timer = 20;
    private Handler handler;
    private Runnable runnable;
    private ProgressBar progressBar;
    private TextView txtQuestion, txtQuestionNo, txtPoint, progressCount;
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

        int position = StaticData.currentQuestion - 1;
        String answer = Html.fromHtml(StaticData.questions.get(position).getCorrectAnswer()).toString();

        if (buttonText.equals(answer)) {
            btn.setBackgroundResource(R.drawable.btn_background_success);
            handleIncrementPoint(position);
        } else {
            btn.setBackgroundResource(R.drawable.btn_background_wrong);
            findCorrectAnswer(answer);
        }
    }

    private void handleIncrementPoint(int position) {
        if (StaticData.mode == StaticData.Mode.MULTI){
            if (StaticData.turn == StaticData.Turn.USER1){
                StaticData.user1Point++;
            }
            else {
                StaticData.user2Point++;
            }
        }
        else {
            if (StaticData.questions.get(position).getDifficulty().equals("easy")){
                StaticData.score += 10;
            }
            else if (StaticData.questions.get(position).getDifficulty().equals("medium")){
                StaticData.score += 20;
            }
            else if (StaticData.questions.get(position).getDifficulty().equals("hard")){
                StaticData.score += 30;
            }
            StaticData.point++;
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

        runnable = new Runnable() {
            public void run() {
                handleNextQuestion();
            }
        };
        handler.postDelayed(runnable,1000);
    }

    private void handleNextQuestion() {
        StaticData.currentQuestion++;

        if (StaticData.currentQuestion - 1 < StaticData.numberOfQuestions) {
            intent = new Intent(QuestionActivity.this, QuestionActivity.class);
        } else {
            handleFinishQuestions();
        }

        handler.removeCallbacks(runnable);
        startActivity(intent);
        finish();
    }

    private void handleFinishQuestions(){
        if (StaticData.mode == StaticData.Mode.MULTI){
            if (StaticData.turn == StaticData.Turn.USER1){
                intent = new Intent(QuestionActivity.this, MultiHoldActivity.class);
                StaticData.turn = StaticData.Turn.USER2;
                StaticData.currentQuestion = 1;
            }
            else {
                intent = new Intent(QuestionActivity.this, ResultActivity.class);
                StaticData.currentQuestion = 1;
            }
        }
        else if (StaticData.mode == StaticData.Mode.NORMAL){
            intent = new Intent(QuestionActivity.this, ResultActivity.class);
            StaticData.currentQuestion = 1;
        }
    }

    private void initQuestion() {
        int position = StaticData.currentQuestion - 1;
        answers.clear();
        answers = (ArrayList<String>) StaticData.questions.get(position).getIncorrectAnswers().clone();
        answers.add(StaticData.questions.get(position).getCorrectAnswer());
        Collections.shuffle(answers);

        initPoints();
        txtQuestion.setText(Html.fromHtml(StaticData.questions.get(position).getQuestion()));

        btnA.setText(Html.fromHtml(answers.get(0)));
        btnB.setText(Html.fromHtml(answers.get(1)));
        btnC.setText(Html.fromHtml(answers.get(2)));
        btnD.setText(Html.fromHtml(answers.get(3)));

        txtQuestionNo.setText(StaticData.currentQuestion + "/" + StaticData.numberOfQuestions);
    }

    private void initPoints() {
        if (StaticData.mode == StaticData.Mode.MULTI){
            if (StaticData.turn == StaticData.Turn.USER1){
                txtPoint.setText(String.valueOf(StaticData.user1Point));
            }
            else {
                txtPoint.setText(String.valueOf(StaticData.user2Point));
            }
        }
        else {
            txtPoint.setText(String.valueOf(StaticData.score));
        }

    }

    private void initTimer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (timer == 0) {
                    handler.removeCallbacks(runnable);
                    int position = StaticData.currentQuestion - 1;
                    String answer = Html.fromHtml(StaticData.questions.get(position).getCorrectAnswer()).toString();
                    findCorrectAnswer(answer);
                }
                if (timer != 20){
                    progressBar.setProgress(progressBar.getProgress() - 5);
                }

                progressCount.setText(String.valueOf(timer));
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
        progressCount = findViewById(R.id.progressCount);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        answers = new ArrayList<>();
    }
}
