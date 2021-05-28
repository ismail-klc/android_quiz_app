package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticData;
import com.ismailkilic.quizapp.data.ApiClient;
import com.ismailkilic.quizapp.data.RestInterface;
import com.ismailkilic.quizapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    RestInterface restInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().hide();
        restInterface = ApiClient.getClient().create(RestInterface.class);
    }

    public void handleSelectCategory(View view) {
        Button button = (Button) view;
        String value = (String) button.getHint();
        StaticData.selectedCategory = value;
        StaticData.selectedCategoryName = (String) button.getText();

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(CategoryActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Questions loading....");
        progressDialog.show();

        Call<Result> call = null;
        if (!StaticData.selectedCategory.isEmpty() && !StaticData.selectedDifficulty.isEmpty()){
            call = restInterface.getQuestions(StaticData.selectedCategory, StaticData.selectedDifficulty);
        }
        else if(!StaticData.selectedCategory.isEmpty()){
            call = restInterface.getQuestionsByCategory(StaticData.selectedCategory);
        }
        else if(!StaticData.selectedDifficulty.isEmpty()){
            call = restInterface.getQuestionsByDifficulty(StaticData.selectedDifficulty);
        }
        else {
            call = restInterface.getQuestions();
        }

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                StaticData.questions = response.body().getResults();

                handleNextActivity();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleNextActivity(){
        Intent intent = null;
        if (StaticData.mode == StaticData.Mode.NORMAL){
            intent = new Intent(CategoryActivity.this, QuestionActivity.class);
        }
        else if (StaticData.mode == StaticData.Mode.MULTI){
            intent = new Intent(CategoryActivity.this, MultiHoldActivity.class);
        }
        else if (StaticData.mode == StaticData.Mode.INFINITY){
            intent = new Intent(CategoryActivity.this, QuestionActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
