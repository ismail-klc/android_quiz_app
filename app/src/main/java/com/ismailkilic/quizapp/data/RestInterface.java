package com.ismailkilic.quizapp.data;

import com.ismailkilic.quizapp.models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {

    @GET("api.php?amount=10&type=multiple")
    Call<Result> getQuestions();

    @GET("api.php?amount=10&type=multiple")
    Call<Result> getQuestions(@Query("category") String category, @Query("difficulty") String difficulty);

    @GET("api.php?type=multiple")
    Call<Result> getQuestions(@Query("category") String category, @Query("difficulty") String difficulty, @Query("amount") int amount);

    @GET("api.php?amount=10&type=multiple")
    Call<Result> getQuestionsByCategory(@Query("category") String category);

    @GET("api.php?amount=10&type=multiple")
    Call<Result> getQuestionsByDifficulty(@Query("difficulty") String difficulty);
}
