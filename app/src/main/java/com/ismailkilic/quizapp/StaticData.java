package com.ismailkilic.quizapp;

import com.ismailkilic.quizapp.models.Question;

import java.util.ArrayList;

public class StaticData {
    public enum Mode {
        NORMAL,
        MULTI,
        INFINITY
    }

    public enum Turn {
        USER1,
        USER2
    }

    public static ArrayList<Question> questions = new ArrayList<>();

    // common
    public static Mode mode = Mode.NORMAL;
    public static int point = 0;
    public static int score = 0;
    public static int currentQuestion = 1;
    public static int numberOfQuestions = 10;
    public static String selectedDifficulty = "";
    public static String selectedCategory= "";
    public static String selectedCategoryName= "";

    // multiplayer
    public static Turn turn = Turn.USER1;
    public static String user1 = "";
    public static String user2 = "";
    public static int user1Point = 0;
    public static int user2Point = 0;

    public static void clearValues(){
        turn = Turn.USER1;
        user1 = "";
        user2 = "";
        user1Point = 0;
        user2Point = 0;
        score = 0;

        mode = Mode.NORMAL;
        point = 0;
        currentQuestion = 1;
        numberOfQuestions = 10;
        selectedDifficulty = "";
        selectedCategory= "";
        questions.clear();
    }
}
