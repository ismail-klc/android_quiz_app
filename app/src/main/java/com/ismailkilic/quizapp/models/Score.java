package com.ismailkilic.quizapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "scores")
public class Score {

    @PrimaryKey(autoGenerate = true)
    public int s_id;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "total_questions")
    private int totalQuestions;

    @ColumnInfo(name = "correct_answer")
    private int correctAnswer;

    @ColumnInfo(name = "wrong_answer")
    private int wrongAnswer;

    @ColumnInfo(name = "max_score")
    private int maxScore;

    @ColumnInfo(name = "image")
    private String categoryImage;



    public Score(int id, String category, int totalQuestions, int correctAnswer, int wrongAnswer, int maxScore, String categoryImage) {
        this.s_id = id;
        this.category = category;
        this.totalQuestions = totalQuestions;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
        this.maxScore = maxScore;
        this.categoryImage = categoryImage;
    }

    public Score(String category, int totalQuestions, int correctAnswer, int wrongAnswer, int maxScore, String categoryImage) {
        this.category = category;
        this.totalQuestions = totalQuestions;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
        this.maxScore = maxScore;
        this.categoryImage = categoryImage;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public int getS_Id() {
        return this.s_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(int wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
