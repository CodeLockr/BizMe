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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SurveyTwoActivity extends Activity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private EditText editTextAnswer;
    private RadioButton radioButtonYes;
    private RadioButton radioButtonNo;
    private RadioButton radioButtonTuesday;
    private RadioButton radioButtonSunday;
    private Button buttonSubmit;

    String yes = "0";
    String no = "0";
    String tuesday = "0";
    String sunday = "0";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_two);

        editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        radioButtonNo = (RadioButton) findViewById(R.id.radioButtonNo);
        radioButtonYes = (RadioButton) findViewById(R.id.radioButtonYes);
        radioButtonTuesday = (RadioButton) findViewById(R.id.radioButtonTuesday);
        radioButtonSunday = (RadioButton) findViewById(R.id.radioButtonSunday);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        radioButtonNo.setOnCheckedChangeListener(this);
        radioButtonSunday.setOnCheckedChangeListener(this);
        radioButtonYes.setOnCheckedChangeListener(this);
        radioButtonTuesday.setOnCheckedChangeListener(this);

        buttonSubmit.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(SurveyTwoActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SurveyTwoActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



    }

    @Override
    public void onClick(View v) {

        Toast.makeText(SurveyTwoActivity.this, "Thank you for participating in this Survey! +5 Points Added! ", Toast.LENGTH_LONG).show();


        if (radioButtonYes.isChecked()) {
            yes = "1";
            no = "0";
        } else if (radioButtonNo.isChecked()) {
            no = "1";
            yes = "0";
        }

        if (radioButtonTuesday.isChecked()) {
            tuesday = "1";
            sunday = "0";
        } else if (radioButtonSunday.isChecked() ) {
            sunday = "1";
            tuesday = "0";
        }


        String email = mAuth.getCurrentUser().getEmail();
        String favorite = editTextAnswer.getText().toString();
        String company = "Good Time Charley's";

        SurveyClass surveyClass = new SurveyClass (yes, no, sunday, tuesday, favorite, email, company);

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference dataSurveySubmit = database.getReference("Yes");
        DatabaseReference dataNewSurveySubmit = dataSurveySubmit.push();
        //Toast.makeText(this, surveyClass.yes, Toast.LENGTH_SHORT).show();
        dataNewSurveySubmit.setValue(surveyClass.yes);

        DatabaseReference dataSurveySubmitNo = database.getReference("No");
        DatabaseReference dataNewSurveySubmitNo = dataSurveySubmitNo.push();
        //Toast.makeText(this, surveyClass.yes, Toast.LENGTH_SHORT).show();
        dataNewSurveySubmitNo.setValue(surveyClass.no);

        DatabaseReference dataSurveySubmit2 = database.getReference("Question 2");
        DatabaseReference dataNewSurveySubmit2 = dataSurveySubmit2.push();
        //Toast.makeText(this, surveyClass.yes, Toast.LENGTH_SHORT).show();
        dataNewSurveySubmit2.setValue(surveyClass.favorite);

        DatabaseReference dataSurveySubmit3 = database.getReference("Question 3");
        DatabaseReference dataNewSurveySubmit3 = dataSurveySubmit3.push();
        //Toast.makeText(this, surveyClass.yes, Toast.LENGTH_SHORT).show();
        dataNewSurveySubmit3.setValue(surveyClass.sunday);

        //////////////////////////////
        //   UPDATING USER POINTS   //
        //////////////////////////////

        String compName = surveyClass.company;
        CompanyProgress[] companyArray = new CompanyProgress[1];
        companyArray[0] = new CompanyProgress(compName);

        User user = new User(email, companyArray, 0);

        companyArray[0].compTotal = companyArray[0].compTotal + 5;

        /*
        To calculate aggregate user total points, will eventually be able to loop through a
        larger company array and sum the individual company progress points.

        We were also having problems pushing the entire User object to firebase - that is why the
        below code is only pushing the user total instead of the entire class.

        In future versions, we will have to better structure the User, Company, CompanyProgress,
        and Survey classes so they can all "talk" to each other (for example, the each survey class
        should have a point value attached to it so when completed that value can be added to the
        correct company in the array). This way when we have multiple companies, surveys, and
        users, our app can change the add the points from the survey to the correct user's company
        progress.
        */

        for (int i=0; i<1; i++){
            user.userTotal = user.userTotal + companyArray[i].compTotal;
        }

        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference dataUsers = database2.getReference("Users");
        DatabaseReference dataNewUser = dataUsers.push();
        dataNewUser.setValue(user.userTotal);

        Toast.makeText(SurveyTwoActivity.this, "Thank you for participating in this Survey!\n " +
                "+5 Points Added! ", Toast.LENGTH_LONG).show();

        Intent intentFeed = new Intent (SurveyTwoActivity.this, FeedActivity.class);
        startActivity(intentFeed);


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
        Intent intentMain = new Intent(SurveyTwoActivity.this, MainActivity.class);
        Intent intentFeed = new Intent(SurveyTwoActivity.this, FeedActivity.class);
        Intent intentMap = new Intent(SurveyTwoActivity.this, MapsActivity.class);
        Intent intentProfile = new Intent(SurveyTwoActivity.this, ProfileActivity.class);

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
