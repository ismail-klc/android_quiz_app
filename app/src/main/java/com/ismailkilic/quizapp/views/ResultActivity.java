package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticDatas;

public class ResultActivity extends AppCompatActivity {

    private TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtResult = findViewById(R.id.txtResult);
        txtResult.setText("Point: " + StaticDatas.point);
        StaticDatas.point = 0;
    }
}
