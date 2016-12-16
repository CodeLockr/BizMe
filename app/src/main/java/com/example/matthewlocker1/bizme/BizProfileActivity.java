package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.os.Bundle;

public class BizProfileActivity extends Activity {

    //this is where a business can view and edit their information
    //there will also be a button sending an intent to create a survey or other type of mission

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biz_profile);
    }
}
