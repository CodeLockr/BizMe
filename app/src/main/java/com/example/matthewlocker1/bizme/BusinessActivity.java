package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BusinessActivity extends Activity implements View.OnClickListener {

    //this is the public profile of the business, taking information from the BizProfile activity
    //users will be able to look at different businesses and from this page they can access missions

    private Button buttonMission;
    private Button buttonRedeem;
    private Button buttonSurvey;
    private TextView textViewDeal;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        buttonMission = (Button) findViewById(R.id.buttonMission);
        buttonRedeem = (Button) findViewById(R.id.buttonRedeem);
        buttonSurvey = (Button) findViewById(R.id.buttonSurvey);
        textViewDeal = (TextView) findViewById(R.id.textViewDeal);

        //buttonMission.setOnClickListener(this);
        //buttonMission goes to a broken Survey, use buttonSurvey instead
        buttonRedeem.setOnClickListener(this);
        buttonSurvey.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(BusinessActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BusinessActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dataDeals = database.getReference();
        dataDeals.child("Deals").orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = textViewDeal.getText().toString();
                value = value + "Pitchers Half Off Until 8PM";
                textViewDeal.setText(value);

                //dummy code to show that the deals would show up on the Business screen

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intentMission = new Intent (BusinessActivity.this, SurveyActivity.class);
        Intent intentSurvey = new Intent (BusinessActivity.this, SurveyTwoActivity.class);

        if (v == buttonMission) {
            startActivity(intentMission);
        } else if (v == buttonRedeem) {
            Toast.makeText(BusinessActivity.this, "Redeem Activity Coming Soon!", Toast.LENGTH_SHORT).show();
        } else if (v == buttonSurvey) {
            startActivity(intentSurvey);
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
        Intent intentMain = new Intent(BusinessActivity.this, MainActivity.class);
        Intent intentFeed = new Intent(BusinessActivity.this, FeedActivity.class);
        Intent intentMap = new Intent(BusinessActivity.this, MapsActivity.class);
        Intent intentProfile = new Intent(BusinessActivity.this, ProfileActivity.class);

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
