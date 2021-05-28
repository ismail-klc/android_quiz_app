package com.ismailkilic.quizapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.ismailkilic.quizapp.models.Score;

@Database(entities = {Score.class}, version = 6)
public abstract class RepoDatabase extends RoomDatabase {
    private static final String DB_NAME = "quiz_score.db";
    private static volatile  RepoDatabase instance;

    public static synchronized RepoDatabase getInstance(Context context){
        if(instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static RepoDatabase create(final Context context){
        return Room.databaseBuilder(
                context,
                RepoDatabase.class,
                DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract ScoreDal getScoreDal();
}
