package com.ismailkilic.quizapp.models;

import java.util.ArrayList;

public class Question {
    private String category;
    private String correctAnswer;
    private String difficulty;
    private ArrayList<String> incorrectAnswers;
    private String question;

    public Question(String category, String correctAnswer, String difficulty, ArrayList<String> incorrectAnswers, String question) {
        this.category = category;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.incorrectAnswers = incorrectAnswers;
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
