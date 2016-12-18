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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BizRegActivity extends Activity implements View.OnClickListener {

    //this activity is where a business is taken if they have not yet signed up (from BizLogin)
    //businesses will create an account using an email and password and will be taken to BizProfile

    private TextView textViewLogo;
    private TextView textTerms;
    private TextView textViewTerms;
    private TextView textViewAlready;
    private EditText editTextRegEmail;
    private EditText editTextRegPassword;
    private EditText editTextName;
    private EditText editTextLong;
    private EditText editTextLat;
    private EditText editTextDescription;

    private Button buttonSignup;
    private Button buttonLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biz_reg);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(BizRegActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BizRegActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };

        textViewLogo = (TextView) findViewById(R.id.textViewLogo);
        textViewTerms = (TextView) findViewById(R.id.textViewTerms);
        textViewAlready = (TextView) findViewById(R.id.textViewAlready);
        editTextRegEmail = (EditText) findViewById(R.id.editTextRegEmail);
        editTextRegPassword = (EditText) findViewById(R.id.editTextRegPassword);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLong = (EditText) findViewById(R.id.editTextLong);
        editTextLat = (EditText) findViewById(R.id.editTextLat);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonSignup.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
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
        Intent intentMain = new Intent(BizRegActivity.this, MainActivity.class);
        Intent intentFeed = new Intent(BizRegActivity.this, FeedActivity.class);
        Intent intentMap = new Intent(BizRegActivity.this, MapsActivity.class);
        Intent intentProfile = new Intent(BizRegActivity.this, ProfileActivity.class);

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
    public void onClick(View view) {

        String email = editTextRegEmail.getText().toString();
        String password = editTextRegPassword.getText().toString();

        if (view == buttonSignup) {

            String companyName = editTextName.getText().toString();
            String bizLat = editTextLat.getText().toString();
            String bizLong = editTextLong.getText().toString();
            String description = editTextDescription.getText().toString();

            Company company = new Company(email, password, companyName, bizLat,
                    bizLong, description);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Companies");
            DatabaseReference dataNewCompany = myRef.push();
            dataNewCompany.setValue(company);

            createAccount(email, password);

        }
        else if (view == buttonLogin) {
            Intent intentLog = new Intent(BizRegActivity.this, BizLoginActivity.class);
            startActivity(intentLog);
        }
    }

    public void createAccount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(BizRegActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BizRegActivity.this, "Registration successful",
                                    Toast.LENGTH_SHORT).show();

                            Intent intentTutorial = new Intent(BizRegActivity.this,
                                    TutorialActivity.class);
                            startActivity(intentTutorial);
                        }


                    }
                });

    }


}
