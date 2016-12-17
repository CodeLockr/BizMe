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

public class BizSurveyViewActivity extends Activity implements View.OnClickListener {

    private TextView textViewAnswers;

    private TextView textViewAnswer1;
    private TextView textViewAnswer2;
    private TextView textViewAnswer3;
    private TextView textViewAnswer4;
    private TextView textViewAnswer5;
    private TextView textViewAnswer6;
    private TextView textViewAnswer7;
    private TextView textViewAnswer8;
    private TextView textViewAnswer9;

    private Button buttonUpdate;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biz_survey_view);

        textViewAnswers = (TextView) findViewById(R.id.textViewAnswers);

        textViewAnswer1 = (TextView) findViewById(R.id.textViewAnswer1);
        textViewAnswer2 = (TextView) findViewById(R.id.textViewAnswer2);
        textViewAnswer3 = (TextView) findViewById(R.id.textViewAnswer3);
        textViewAnswer4 = (TextView) findViewById(R.id.textViewAnswer4);
        textViewAnswer5 = (TextView) findViewById(R.id.textViewAnswer5);
        textViewAnswer6 = (TextView) findViewById(R.id.textViewAnswer6);
        textViewAnswer7 = (TextView) findViewById(R.id.textViewAnswer7);
        textViewAnswer8 = (TextView) findViewById(R.id.textViewAnswer8);
        textViewAnswer9 = (TextView) findViewById(R.id.textViewAnswer9);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(BizSurveyViewActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BizSurveyViewActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }


    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataSurveyResults = database.getReference();
        dataSurveyResults.child("SurveyResults").orderByKey().limitToLast(2).addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                SurveyResult surveyResult = dataSnapshot.getValue(SurveyResult.class);
                String val = textViewAnswers.getText().toString();
                val = val + "\n \n User: " + surveyResult.email + "\n Answer 1: " + surveyResult.answer1 + "\n Answer 2: "
                + surveyResult.answer2 + "\n Answer 3: " + surveyResult.answer3 + "\n Answer 4: " + surveyResult.answer4 + "\n Answer 5: "
                        + surveyResult.answer5 + "\n Answer 6: " + surveyResult.answer6 + "\n Answer 7: "
                        + surveyResult.answer7 + "\n Answer 8: " + surveyResult.answer8 + "\n Answer 9: " + surveyResult.answer9;
                //+ postObject.postTime + "\n";
                textViewAnswers.setText(val);


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
        Intent intentMain = new Intent(BizSurveyViewActivity.this, MainActivity.class);
        Intent intentFeed = new Intent(BizSurveyViewActivity.this, FeedActivity.class);
        Intent intentMap = new Intent(BizSurveyViewActivity.this, MapsActivity.class);
        Intent intentProfile = new Intent(BizSurveyViewActivity.this, ProfileActivity.class);

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