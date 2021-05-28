package com.ismailkilic.quizapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.data.RepoDatabase;
import com.ismailkilic.quizapp.data.ScoreDal;
import com.ismailkilic.quizapp.models.Score;

import java.util.List;

public class ScoresActivity extends AppCompatActivity {

    private MyAdapter adapter;
    private RepoDatabase database;
    private ScoreDal scoreDal;
    List<Score> scores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        getSupportActionBar().hide();
        getScores();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(this.getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new MyAdapter(this);
        adapter.setScores(scores);
        recyclerView.setAdapter(adapter);
    }

    private void getScores() {
        database = RepoDatabase.getInstance(getApplicationContext());
        scoreDal = database.getScoreDal();
        scores = scoreDal.getAllScores();

    }
}
