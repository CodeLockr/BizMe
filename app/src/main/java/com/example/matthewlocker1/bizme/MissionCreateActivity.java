package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.os.Bundle;

public class MissionCreateActivity extends Activity {

    //this is a page for the businesses to create a new mission for users to activate
    //there will be several options for them on a radio button to select mission type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_create);
    }
}
