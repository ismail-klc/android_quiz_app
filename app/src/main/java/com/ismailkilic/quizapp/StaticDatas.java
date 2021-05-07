package com.ismailkilic.quizapp;

import com.ismailkilic.quizapp.models.Question;

import java.util.ArrayList;

public class StaticDatas {
    public static ArrayList<Question> questions = new ArrayList<>();

    public static int point = 0;
    public static int currentQuestion = 1;
    public static int numberOfQuestions = 10;
    public static String selectedDifficulty = "";
    public static String selectedCategory= "";
}
