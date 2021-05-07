package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticDatas;

public class DifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        getSupportActionBar().hide();
    }

    public void handleSelectDifficulty(View view) {
        Button button = (Button) view;
        String value = (String) button.getHint();
        StaticDatas.selectedDifficulty = value;
        Intent intent = new Intent(DifficultyActivity.this, CategoryActivity.class);
        startActivity(intent);
        finish();
    }
}
