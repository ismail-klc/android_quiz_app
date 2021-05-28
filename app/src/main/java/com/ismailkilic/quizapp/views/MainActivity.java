package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticData;
import com.ismailkilic.quizapp.data.ApiClient;
import com.ismailkilic.quizapp.data.RestInterface;
import com.ismailkilic.quizapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RestInterface restInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        restInterface = ApiClient.getClient().create(RestInterface.class);
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
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Questions loading....");
        progressDialog.show();

        Call<Result> call = null;
        call = restInterface.getQuestions();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                StaticData.questions = response.body().getResults();
                StaticData.mode = StaticData.Mode.INFINITY;
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
