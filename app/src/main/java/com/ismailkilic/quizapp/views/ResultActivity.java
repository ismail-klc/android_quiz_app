package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.ismailkilic.quizapp.CategoryImages;
import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticData;
import com.ismailkilic.quizapp.data.RepoDatabase;
import com.ismailkilic.quizapp.data.ScoreDal;
import com.ismailkilic.quizapp.models.Score;

public class ResultActivity extends AppCompatActivity {

    private RepoDatabase database;
    private ScoreDal scoreDal;
    private Score score = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();
        initFragment();

        if (StaticData.mode != StaticData.Mode.MULTI){
            addScoresToDatabase();
        }
    }

    private void addScoresToDatabase() {
        database = RepoDatabase.getInstance(getApplicationContext());
        scoreDal = database.getScoreDal();
        int maxScore = StaticData.score;
        getScore();

        if (score == null){
            Score newScore = new Score(StaticData.selectedCategoryName,
                    StaticData.numberOfQuestions,
                    StaticData.point,
                    StaticData.numberOfQuestions - StaticData.point,
                    maxScore,
                    CategoryImages.getImage(StaticData.selectedCategoryName));
            addScore(newScore);
            System.out.println("added to database");
        }
        else {
            if (score.getMaxScore() > StaticData.score){
                maxScore = score.getMaxScore();
            }

            Score updatedScore = new Score(score.getS_Id(), StaticData.selectedCategoryName,
                    StaticData.numberOfQuestions + score.getTotalQuestions(),
                    StaticData.point + score.getCorrectAnswer(),
                    StaticData.numberOfQuestions - StaticData.point + score.getWrongAnswer(),
                    maxScore,
                    CategoryImages.getImage(StaticData.selectedCategoryName));
            updateScore(updatedScore);
            System.out.println("updated");
        }
    }

    private void updateScore(final Score updatedScore) {
        scoreDal.update(updatedScore);
    }

    private void addScore(final Score newScore) {
        scoreDal.insert(newScore);
    }

    private void getScore() {
        score = scoreDal.getScore(StaticData.selectedCategoryName);
    }

    private void initFragment() {
        if (StaticData.mode == StaticData.Mode.MULTI){
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
        StaticData.clearValues();
        finish();
    }
}
