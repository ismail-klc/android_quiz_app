package com.ismailkilic.quizapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ismailkilic.quizapp.models.Score;
import java.util.List;

@Dao
public interface ScoreDal {

    @Query("SELECT * FROM scores")
    List<Score> getAllScores();

    @Query("SELECT * FROM scores WHERE category = :category ")
    Score getScore(String category);

    @Insert
    void insert(Score... score);

    @Update
    void update(Score... score);
}
