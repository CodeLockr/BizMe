package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TutorialActivity extends Activity implements View.OnClickListener{

    private Button buttonStart;
    private TextView textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        buttonStart = (Button) findViewById(R.id.buttonStart);
        textView7 = (TextView) findViewById(R.id.textView7);

        buttonStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonStart) {
            Intent intentFeed = new Intent(TutorialActivity.this, FeedActivity.class);
            startActivity(intentFeed);
        }
    }
}
