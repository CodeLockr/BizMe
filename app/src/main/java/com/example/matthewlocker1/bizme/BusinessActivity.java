package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.os.Bundle;

public class BusinessActivity extends Activity {

    //this is the public profile of the business, taking information from the BizProfile activity
    //users will be able to look at different businesses and from this page they can access missions


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
    }
}
