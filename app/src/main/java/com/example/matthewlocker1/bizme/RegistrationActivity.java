package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends Activity implements View.OnClickListener {
    private Button buttonSignup;
    private EditText editTextRegEmail;
    private EditText editTextRegPassword;
    private Button buttonGotoLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    //this is where users can register, and they will be sent from the Login screen
    //after registering, users will immediately login and be sent to the FeedActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        editTextRegEmail = (EditText) findViewById(R.id.editTextRegEmail);
        editTextRegPassword = (EditText) findViewById(R.id.editTextRegPassword);
        buttonGotoLogin = (Button) findViewById(R.id.buttonGotoLogin);

        buttonSignup.setOnClickListener(this);
        buttonGotoLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(RegistrationActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        String email = editTextRegEmail.getText().toString();
        String password = editTextRegPassword.getText().toString();

        if (view == buttonSignup) {
            createAccount(email, password);

        } else if (view == buttonGotoLogin) {
            Intent intentLogin = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intentLogin);

        }

    }

    public void createAccount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Registration succesful", Toast.LENGTH_SHORT).show();
                            Intent intentFeed = new Intent(RegistrationActivity.this, FeedActivity.class);
                            startActivity(intentFeed);

                            /*
                            new AlertDialog.Builder(MainActivity.this)
                                    .setMessage("Where?")
                                    .setNegativeButton("Go to Feed", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialogInterface, int i) {
                                            Intent intentFeed = new Intent(MainActivity.this, FeedActivity.class);
                                            startActivity(intentFeed);
// add Toasts to this for notification
                                        }
                                    })
                                    .setPositiveButton("Go To Map View", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intentMap = new Intent(MainActivity.this, MapsActivity.class);
                                            startActivity(intentMap);
                                        }


                                    })
                                    .show(); */
                        }


                    }
                });

    }}




