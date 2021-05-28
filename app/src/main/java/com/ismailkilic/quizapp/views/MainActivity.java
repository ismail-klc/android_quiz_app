package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StaticData.clearValues();
    }

    public void handleScores(View view) {
        Intent intent = new Intent(MainActivity.this, ScoresActivity.class);
        startActivity(intent);
    }

    public void handleMulti(View view) {
        StaticData.mode = StaticData.Mode.MULTI;
        Intent intent = new Intent(MainActivity.this, MultiActivity.class);
        startActivity(intent);
    }

    public void handleStart(View view) {
        StaticData.mode = StaticData.Mode.NORMAL;
        Intent intent = new Intent(MainActivity.this, DifficultyActivity.class);
        startActivity(intent);
    }

    public void handleInf(View view) {
    }
}
