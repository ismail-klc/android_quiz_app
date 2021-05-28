package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticData;

public class MultiHoldActivity extends AppCompatActivity {

    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_hold);
        getSupportActionBar().hide();
        initVariables();
    }

    private void initVariables() {
        username = findViewById(R.id.hold_userName);

        if (StaticData.turn == StaticData.Turn.USER1){
            username.setText(StaticData.user1);
        }
        else {
            username.setText(StaticData.user2);
        }
    }

    public void handleHoldStart(View view) {
        Intent intent = new Intent(MultiHoldActivity.this, QuestionActivity.class);
        startActivity(intent);
        finish();
    }
}
