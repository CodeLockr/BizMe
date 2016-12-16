package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText editTextLoginEmail;
    private EditText editTextLoginPass;
    private Button buttonGotoSignup;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin= (Button) findViewById(R.id.buttonLogin);
        editTextLoginEmail= (EditText) findViewById(R.id.editTextLoginEmail);
        editTextLoginPass=(EditText) findViewById(R.id.editTextLoginPass);
        buttonGotoSignup= (Button) findViewById(R.id.buttonGotoSignup);

        buttonLogin.setOnClickListener(this);
        buttonGotoSignup.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        String email= editTextLoginEmail.getText().toString();
        String password= editTextLoginPass.getText().toString();

        if (view ==buttonLogin) {
            signIn(email, password);

        }else if (view==buttonGotoSignup){
            Intent intentSignup= new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intentSignup);

        }

    }

    public void signIn (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intentFeed = new Intent(MainActivity.this, FeedActivity.class);
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

