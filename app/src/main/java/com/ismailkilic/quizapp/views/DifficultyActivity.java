package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ismailkilic.quizapp.R;

public class DifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        getSupportActionBar().hide();
    }
}
