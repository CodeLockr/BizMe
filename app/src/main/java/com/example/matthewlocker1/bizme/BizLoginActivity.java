package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class BizLoginActivity extends Activity {

    //This activity is where the business will log in and takes them to BizProfile

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biz_login);
    }
}
