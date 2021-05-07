package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticDatas;

public class ResultActivity extends AppCompatActivity {

    private TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();
        txtResult = findViewById(R.id.txtResult);
        txtResult.setText("Point: " + StaticDatas.point);
    }

    public void handleGoHome(View view) {
        StaticDatas.selectedDifficulty = "";
        StaticDatas.selectedCategory = "";
        StaticDatas.questions.clear();
        StaticDatas.point = 0;
        finish();
    }
}
