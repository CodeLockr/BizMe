package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.os.Bundle;

public class BizRegActivity extends Activity {

    //this activity is where a business is taken if they have not yet signed up (from BizLogin)
    //businesses will create an account using an email and password and will be taken to BizProfile

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biz_reg);
    }
}
