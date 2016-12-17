package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SurveyActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    //this is a sample survey, part of the missions, and will draw data created by businesses in the MissionCreateActivity
    //users access this survey from the business page and will be taken back to the feed activity after completing the survey
    //users will receive points for submitting a survey or completing a mission, and these points will be reflected on Profile


    private Button buttonSubmit;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private RadioButton radioButton6;
    private RadioButton radioButton7;
    private RadioButton radioButton8;
    private RadioButton radioButton9;

    private int Answer1 = 0;
    private int Answer2 = 0;
    private int Answer3 = 0;
    private int Answer4 = 0;
    private int Answer5 = 0;
    private int Answer6 = 0;
    private int Answer7 = 0;
    private int Answer8 = 0;
    private int Answer9 = 0;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        radioButton6 = (RadioButton) findViewById(R.id.radioButton6);
        radioButton7 = (RadioButton) findViewById(R.id.radioButton7);
        radioButton8 = (RadioButton) findViewById(R.id.radioButton8);
        radioButton9 = (RadioButton) findViewById(R.id.radioButton9);


        buttonSubmit.setOnClickListener(this);
        radioButton1.setOnCheckedChangeListener(this);
        radioButton2.setOnCheckedChangeListener(this);
        radioButton3.setOnCheckedChangeListener(this);
        radioButton4.setOnCheckedChangeListener(this);
        radioButton5.setOnCheckedChangeListener(this);
        radioButton6.setOnCheckedChangeListener(this);
        radioButton7.setOnCheckedChangeListener(this);
        radioButton8.setOnCheckedChangeListener(this);
        radioButton9.setOnCheckedChangeListener(this);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(SurveyActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SurveyActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


    @Override
    public void onClick(View v) {

        if (v == buttonSubmit) {
            Toast.makeText(SurveyActivity.this, "Thank you for participating in this Survey! +5 Points Added! ", Toast.LENGTH_LONG).show();

            surveyComplete();

            Intent intentFeed = new Intent (SurveyActivity.this, FeedActivity.class);
            startActivity(intentFeed);

        }

    }

    public void surveyComplete() {

       /* int Answer = 0;
        int Answer2 = 0;
        int Answer3 = 0;
        int Answer4 = 0;
        int Answer5 = 0;
        int Answer6 = 0;
        int Answer7 = 0;
        int Answer8 = 0;
        int Answer9 = 0;
        */

        //String answer = radioButton.getText().toString();
        //String answer2 = radioButton2.getText().toString();


        String answer1 = Integer.toString(Answer1);
        String answer2 = Integer.toString(Answer2);
        String answer3 = Integer.toString(Answer3);
        String answer4 = Integer.toString(Answer4);
        String answer5 = Integer.toString(Answer5);
        String answer6 = Integer.toString(Answer6);
        String answer7 = Integer.toString(Answer7);
        String answer8 = Integer.toString(Answer8);
        String answer9 = Integer.toString(Answer9);

        String email = mAuth.getCurrentUser().getEmail();
        //String postTime = "";

        SurveyResult surveyResult = new SurveyResult (answer1, answer2, answer3, answer4, answer5,
                answer6, answer7, answer8, answer9, email);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataSurveyResults = database.getReference("Survey Results");
        DatabaseReference dataNewSurveyResults = dataSurveyResults.push();
        dataNewSurveyResults.setValue(surveyResult);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        //original attempt at recording survey results but remained local on devices, used firebase

        /*int Answer1 = 0;
        int Answer2 = 0;
        int Answer3 = 0;
        int Answer4 = 0;
        int Answer5 = 0;
        int Answer6 = 0;
        int Answer7 = 0;
        int Answer8 = 0;
        int Answer9 = 0;*/

        if (radioButton1.isChecked()) {
            Answer1++;
        }

        else if (radioButton2.isChecked()) {
            Answer2++;
        }

        else if (radioButton3.isChecked()) {
            Answer3++;
        }

        if (radioButton4.isChecked()) {
            Answer4++;
        }

        else if (radioButton5.isChecked()) {
            Answer5++;
        }

        else if (radioButton6.isChecked()) {
            Answer6++;
        }

        if (radioButton7.isChecked()) {
            Answer7++;
        }

        else if (radioButton8.isChecked()) {
            Answer8++;
        }

        else if (radioButton9.isChecked()) {
            Answer9++;
        }


    }


    //added menus

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intentMain = new Intent(SurveyActivity.this, MainActivity.class);
        Intent intentFeed = new Intent(SurveyActivity.this, FeedActivity.class);
        Intent intentMap = new Intent(SurveyActivity.this, MapsActivity.class);
        Intent intentProfile = new Intent(SurveyActivity.this, ProfileActivity.class);

        if (mAuth.getCurrentUser() != null) {
            if (item.getItemId() == R.id.menuLogout) {
                mAuth.signOut();
                startActivity(intentMain);
            } else if (item.getItemId() == R.id.menuFeed) {
                startActivity(intentFeed);
            } else if (item.getItemId () == R.id.menuMap){
                startActivity(intentMap);
            } else if (item.getItemId() == R.id.menuProfile) {
                startActivity(intentProfile);
            }

        } else {
            Toast.makeText(this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
