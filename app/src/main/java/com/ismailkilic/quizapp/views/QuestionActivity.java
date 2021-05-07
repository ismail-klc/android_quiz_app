package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ismailkilic.quizapp.R;

public class QuestionActivity extends AppCompatActivity {

    private int timer = 20;
    private Handler handler;
    private Runnable runnable;
    private ProgressBar progressBar;
    private TextView txtQuestion;
    private Button btnA, btnB, btnC, btnD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().hide();
        initVariables();
        initTimer();
    }

    private void initTimer(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (timer == 0){
                    handler.removeCallbacks(runnable);
                    Toast.makeText(getApplicationContext(), "Time is up", Toast.LENGTH_SHORT).show();
                }
                int progress = progressBar.getProgress();
                progressBar.setProgress(progress - 5);
                timer--;
                handler.postDelayed(runnable,1000);
            }
        };

        handler.post(runnable);
    }

    private void initVariables(){
        progressBar = findViewById(R.id.progressBar);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestion.setText("adsadadsadsadafsa\nadasfasdasdasdas");
    }
}
