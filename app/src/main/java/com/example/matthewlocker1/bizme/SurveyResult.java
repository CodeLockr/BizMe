package com.example.matthewlocker1.bizme;

/**
 * Created by matthewlocker1 on 12/17/16.
 */

public class SurveyResult {

    String email;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String answer5;
    String answer6;
    String answer7;
    String answer8;
    String answer9;


    public SurveyResult () {

    }

    public SurveyResult (String answer1, String answer2, String answer3, String answer4,
                         String answer5, String answer6, String answer7, String answer8,
                         String answer9, String email) {
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.answer6 = answer6;
        this.answer7 = answer7;
        this.answer8 = answer8;
        this.answer9 = answer9;

        this.email = email;


    }



}
