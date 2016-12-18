package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BizLoginActivity extends Activity implements View.OnClickListener {

    private TextView textViewLogo;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonSignup;
    private Button buttonChange;

    //This activity is where the business will log in and takes them to BizProfile

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biz_login);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(BizLoginActivity.this, "User Logged In: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BizLoginActivity.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };

        textViewLogo = (TextView) findViewById(R.id.textViewLogo);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        buttonChange = (Button) findViewById(R.id.buttonChange);

        buttonLogin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
        buttonChange.setOnClickListener(this);
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
        Intent intentMain = new Intent(BizLoginActivity.this, MainActivity.class);
        Intent intentFeed = new Intent(BizLoginActivity.this, FeedActivity.class);
        Intent intentMap = new Intent(BizLoginActivity.this, MapsActivity.class);
        Intent intentProfile = new Intent(BizLoginActivity.this, ProfileActivity.class);

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

        String email = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (v == buttonLogin){
            signIn(email, password);
        }
        else if (v == buttonSignup){
            Intent intentBizReg = new Intent(BizLoginActivity.this, BizRegActivity.class);
            startActivity(intentBizReg);
        }
        else if (v == buttonChange){
            Intent intentSwitchType = new Intent(BizLoginActivity.this, MainActivity.class);
            startActivity(intentSwitchType);
        }

        else{
            Toast.makeText(this, "Oops, something went wrong", Toast.LENGTH_SHORT).show();
        }

    }

    public void signIn (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(BizLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intentFeed = new Intent(BizLoginActivity.this, FeedActivity.class);
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

    }
}
