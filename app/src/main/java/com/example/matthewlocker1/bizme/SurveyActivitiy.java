package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.os.Bundle;

public class SurveyActivitiy extends Activity {

    //this is a sample survey, part of the missions, and will draw data created by businesses in the MissionCreateActivity
    //users access this survey from the business page and will be taken back to the business page after completing the survey

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_activitiy);
    }
}
