package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticDatas;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void handleScores(View view) {
    }

    public void handleMulti(View view) {
        StaticDatas.mode = StaticDatas.Mode.MULTI;
        Intent intent = new Intent(MainActivity.this, MultiActivity.class);
        startActivity(intent);
    }

    public void handleStart(View view) {
        StaticDatas.mode = StaticDatas.Mode.NORMAL;
        Intent intent = new Intent(MainActivity.this, DifficultyActivity.class);
        startActivity(intent);
    }

    public void handleInf(View view) {
    }
}
