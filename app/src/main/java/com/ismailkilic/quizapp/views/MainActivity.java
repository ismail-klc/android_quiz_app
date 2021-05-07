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
    }

    public void handleStart(View view) {
        Intent intent = new Intent(MainActivity.this, DifficultyActivity.class);
        startActivity(intent);
    }
}
