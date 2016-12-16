package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.os.Bundle;

public class RegistrationActivity extends Activity {

    //this is where users can register, and they will be sent from the Login screen
    //after registering, users will immediately login and be sent to the FeedActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
}
