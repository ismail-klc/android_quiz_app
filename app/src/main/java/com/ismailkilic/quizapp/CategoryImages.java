package com.ismailkilic.quizapp;

public class CategoryImages {
    private static String sports = "@drawable/ic_sports_white";
    private static String geography = "@drawable/ic_geography";
    private static String history = "@drawable/ic_history";
    private static String generalKnowledge = "@drawable/ic_general_knowledge_white";
    private static String anyCategory = "@drawable/ic_any_category_white";
    private static String infinity = "@drawable/ic_infinity";

    public static String getImage(String category){
        switch (category){
            case "Sports":
                return sports;
            case "Geography":
                return geography;
            case "History":
                return history;
            case "General Knowledge":
                return generalKnowledge;
            case "Any Category":
                return anyCategory;
            case "Infinity":
                return infinity;
            default:
                return "";
        }
    }
}
