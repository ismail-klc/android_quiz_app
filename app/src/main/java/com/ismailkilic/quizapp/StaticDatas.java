package com.ismailkilic.quizapp;

import com.ismailkilic.quizapp.models.Question;

import java.util.ArrayList;

public class StaticDatas {
    public static ArrayList<Question> questions = new ArrayList<Question>(){{
        add(new Question("Sports", "Eagle", "easy",
                new ArrayList<String>(){
            {add("Birdie");add("Bogey");add("Albatross");}},
                "In golf, what name is given to a hole score of two under par?"));

        add(new Question("Sports", "25", "easy",
                new ArrayList<String>(){
                    {add("19");add("69");add("41");}},
                "How many points did LeBron James score in his first NBA game?"));

        add(new Question("Sports", "Quebec City", "easy",
                new ArrayList<String>(){
                    {add("Houston");add("New York");add("New Orleans");}},
                "Which city did the former NHL team &quot;The Nordiques&quot; originiate from?"));
    }};

    public static int point = 0;
    public static int currentQuestion = 1;
    public static int numberOfQuestions = 3;
}
