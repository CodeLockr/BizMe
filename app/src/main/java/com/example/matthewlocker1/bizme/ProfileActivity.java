package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.EditText;

public class ProfileActivity extends Activity implements View.OnClickListener {

    //this is where users can view their Profile, edit their picture, and see their points
    //businesses will be at the bottom of their profile so users can see business points as well

    private TextView textViewEmail;
    private ProgressBar progressBarBusiness1;
    private ProgressBar progressBarBusiness2;
    private ProgressBar progressBarMainProgress;
    private ImageView imageViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        progressBarBusiness1 = (ProgressBar) findViewById(R.id.progressBarBusiness1);
        progressBarBusiness2 = (ProgressBar) findViewById(R.id.progressBarBusiness2);
        progressBarMainProgress = (ProgressBar) findViewById(R.id.progressBarMainProgress);
        imageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);
    }

    @Override
    public void onClick(View v) {

    }
}
