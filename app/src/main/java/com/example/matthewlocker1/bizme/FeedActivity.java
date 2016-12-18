package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class FeedActivity extends Activity implements View.OnClickListener {

    //this is the main feed where users are taken immediately after login, like a "home screen"
    //there will be a scrolling list view of businesses that are updated and filterable
    //missions will appear on the feed view and be indicated by a color around the business
    //firebase will read business information on the business information screen for the feed


    private Button buttonBiz1;
    private Button buttonBiz2;
    private Button buttonBiz3;
    private Button buttonBiz4;
    private Button buttonToBusiness;
    private ImageView imageViewBiz1;
    private ImageView imageViewBiz2;
    private ImageView imageViewBiz3;
    private ImageView imageViewBiz4;
    private TextView textViewBiz1;
    private TextView textViewBiz2;
    private TextView textViewBiz3;
    private TextView textViewBiz4;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        buttonToBusiness = (Button) findViewById(R.id.buttonToBusiness);
        buttonBiz1 = (Button) findViewById(R.id.buttonBiz1);
        buttonBiz2 = (Button) findViewById(R.id.buttonBiz2);
        buttonBiz3 = (Button) findViewById(R.id.buttonBiz3);
        buttonBiz4 = (Button) findViewById(R.id.buttonBiz4);
        imageViewBiz1 = (ImageView) findViewById(R.id.imageViewBiz1);
        imageViewBiz2 = (ImageView) findViewById(R.id.imageViewBiz2);
        imageViewBiz3 = (ImageView) findViewById(R.id.imageViewBiz3);
        imageViewBiz4 = (ImageView) findViewById(R.id.imageViewBiz4);
        textViewBiz1 = (TextView) findViewById(R.id.textViewBiz1);
        textViewBiz2 = (TextView) findViewById(R.id.textViewBiz2);
        textViewBiz3 = (TextView) findViewById(R.id.textViewBiz3);
        textViewBiz4 = (TextView) findViewById(R.id.textViewBiz4);

        buttonToBusiness.setOnClickListener(this);
        buttonBiz1.setOnClickListener(this);
        buttonBiz2.setOnClickListener(this);
        buttonBiz3.setOnClickListener(this);
        buttonBiz4.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(FeedActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FeedActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }


    @Override
    public void onClick(View v) {

        if (v == buttonBiz1) {
            Intent intentBiz = new Intent(FeedActivity.this, BusinessActivity.class);
            startActivity(intentBiz);
        } else if (v == buttonBiz2) {
            Intent intentBiz2 = new Intent(FeedActivity.this, BusinessActivity.class);
            startActivity(intentBiz2);
            Toast.makeText(this, "Beta Version Only Has Charley's, Redirecting Now", Toast.LENGTH_SHORT).show();
        } else if (v == buttonBiz3) {
            Intent intentBiz3 = new Intent(FeedActivity.this, BusinessActivity.class);
            startActivity(intentBiz3);
            Toast.makeText(this, "Beta Version Only Has Charley's, Redirecting Now", Toast.LENGTH_SHORT).show();
        }  else if (v == buttonBiz4) {
            Intent intentBiz4 = new Intent(FeedActivity.this, BusinessActivity.class);
            startActivity(intentBiz4);
            Toast.makeText(this, "Beta Version Only Has Charley's, Redirecting Now", Toast.LENGTH_SHORT).show();
        } else if (v == buttonToBusiness){
            Intent intentBusinessView = new Intent (FeedActivity.this, BizProfileActivity.class);
            startActivity(intentBusinessView);
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
        Intent intentMain = new Intent(FeedActivity.this, MainActivity.class);
        //Intent intentFeed = new Intent(FeedActivity.this.this, FeedActivity.class);
        Intent intentMap = new Intent(FeedActivity.this, MapsActivity.class);
        Intent intentProfile = new Intent(FeedActivity.this, ProfileActivity.class);

        if (mAuth.getCurrentUser() != null) {
            if (item.getItemId() == R.id.menuLogout) {
                mAuth.signOut();
                startActivity(intentMain);
            } else if (item.getItemId() == R.id.menuFeed) {
                Toast.makeText(this, "You are already in the Feed Activity", Toast.LENGTH_SHORT).show();
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
