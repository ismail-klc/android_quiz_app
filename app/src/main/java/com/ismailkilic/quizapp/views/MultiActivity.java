package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticData;

public class MultiActivity extends AppCompatActivity {

    TextView questionNumber;
    EditText user1, user2;
    int minQuestion = 3, maxQuestion = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        getSupportActionBar().hide();
        initVariables();
    }

    private void initVariables() {
        questionNumber = findViewById(R.id.editQuestionNumber);
        user1 = findViewById(R.id.editUser1);
        user2 = findViewById(R.id.editUser2);
    }

    public void handleContinue(View view) {
        StaticData.turn = StaticData.Turn.USER1;
        StaticData.numberOfQuestions = Integer.parseInt(questionNumber.getText().toString());
        StaticData.user1 = user1.getText().toString();
        StaticData.user2 = user2.getText().toString();

        Intent intent = new Intent(MultiActivity.this, DifficultyActivity.class);
        startActivity(intent);
        finish();
    }

    public void handleDec(View view) {
        int number = Integer.parseInt(questionNumber.getText().toString());
        if (number - 1 > minQuestion - 1)
            questionNumber.setText( String.valueOf(number - 1));
    }

    public void handleInc(View view) {
        int number = Integer.parseInt(questionNumber.getText().toString());
        if (number + 1 < maxQuestion + 1)
            questionNumber.setText(String.valueOf(number + 1));
    }
}
