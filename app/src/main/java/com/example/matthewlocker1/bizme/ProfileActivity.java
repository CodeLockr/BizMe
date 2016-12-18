package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends Activity implements View.OnClickListener {

    //this is where users can view their Profile, edit their picture, and see their points
    //businesses will be at the bottom of their profile so users can see business points as well

    private TextView textViewEmail;
    private ImageView imageViewProfile;

    private Button buttonViewPoints;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        imageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);
        buttonViewPoints = (Button) findViewById(R.id.buttonViewPoints);

        buttonViewPoints.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(ProfileActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(ProfileActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };




    }

    @Override
    public void onClick(View v) {
        if (v == buttonViewPoints){
            // Actual functionality for viewing user points coming soon...
            Toast.makeText(ProfileActivity.this, "You have 5 points!", Toast.LENGTH_SHORT).show();
        }
    }

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
        Intent intentMain = new Intent(ProfileActivity.this, MainActivity.class);
        Intent intentFeed = new Intent(ProfileActivity.this, FeedActivity.class);
        Intent intentMap = new Intent(ProfileActivity.this, MapsActivity.class);
        //Intent intentProfile = new Intent(ProfileActivity.this, ProfileActivity.class);

        if (mAuth.getCurrentUser() != null) {
            if (item.getItemId() == R.id.menuLogout) {
                mAuth.signOut();
                startActivity(intentMain);
            } else if (item.getItemId() == R.id.menuFeed) {
                startActivity(intentFeed);
            } else if (item.getItemId () == R.id.menuMap){
                startActivity(intentMap);
            } else if (item.getItemId() == R.id.menuProfile) {
                Toast.makeText(this, "You are in the Profile Page Already", Toast.LENGTH_SHORT).show();
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
