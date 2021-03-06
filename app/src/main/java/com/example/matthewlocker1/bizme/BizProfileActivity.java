package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BizProfileActivity extends Activity implements View.OnClickListener {

    //this is where a business can view and edit their information
    //there will also be a button sending an intent to create a survey or other type of mission

    private Button buttonViewSurvey;
    private EditText editTextDealCreate;
    private Button buttonSubmitDeal;
    private Button buttonCreateSurvey;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biz_profile);

        buttonViewSurvey = (Button) findViewById(R.id.buttonViewSurvey);
        editTextDealCreate = (EditText) findViewById(R.id.editTextDealCreate);
        buttonSubmitDeal = (Button) findViewById(R.id.buttonSubmitDeal);
        buttonCreateSurvey = (Button) findViewById(R.id.buttonCreateSurvey);

        buttonViewSurvey.setOnClickListener(this);
        buttonSubmitDeal.setOnClickListener(this);
        buttonCreateSurvey.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(BizProfileActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BizProfileActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };
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
        Intent intentMain = new Intent(BizProfileActivity.this, MainActivity.class);
        Intent intentFeed = new Intent(BizProfileActivity.this, FeedActivity.class);
        Intent intentMap = new Intent(BizProfileActivity.this, MapsActivity.class);
        Intent intentProfile = new Intent(BizProfileActivity.this, ProfileActivity.class);

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

    @Override
    public void onClick(View v) {

        if (v == buttonViewSurvey) {
            Intent intentView = new Intent (BizProfileActivity.this, BizSurveyViewActivity.class);
            startActivity(intentView);
        } else if (v == buttonCreateSurvey) {
            Toast.makeText(this, "Create Survey Coming Soon", Toast.LENGTH_SHORT).show();

        } else if (v == buttonSubmitDeal) {
            //insert code for sending the edit text to Firebase
            String deal = editTextDealCreate.getText().toString();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference dataDealSubmit = database.getReference("Deals");
            DatabaseReference dataNewDealSubmit = dataDealSubmit.push();
            dataNewDealSubmit.setValue(deal);
            
            Toast.makeText(this, "Thank you for submitting your deal! Users can now see it on Business Activity", Toast.LENGTH_SHORT).show();
        }


    }
}
