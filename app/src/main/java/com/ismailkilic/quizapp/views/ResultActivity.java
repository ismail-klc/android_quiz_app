package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticDatas;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();
        initFragment();
    }

    private void initFragment() {
        if (StaticDatas.mode == StaticDatas.Mode.MULTI){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            ResultMultiFragment fragment = new ResultMultiFragment();
            fragmentTransaction.replace(R.id.frame_layout,fragment).commit();
        }
        else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            ResultFragment fragment = new ResultFragment();
            fragmentTransaction.replace(R.id.frame_layout,fragment).commit();
        }
    }

    public void handleGoHome(View view) {
        StaticDatas.clearValues();
        finish();
    }
}
