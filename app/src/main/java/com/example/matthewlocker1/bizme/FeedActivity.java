package com.example.matthewlocker1.bizme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class FeedActivity extends Activity implements View.OnClickListener {

    //this is the main feed where users are taken immediately after login, like a "home screen"
    //there will be a scrolling list view of businesses that are updated and filterable
    //missions will appear on the feed view and be indicated by a color around the business


    private Button buttonBiz1;
    private Button buttonBiz2;
    private Button buttonBiz3;
    private Button buttonBiz4;
    private ImageView imageViewBiz1;
    private ImageView imageViewBiz2;
    private ImageView imageViewBiz3;
    private ImageView imageViewBiz4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        buttonBiz1 = (Button) findViewById(R.id.buttonBiz1);
        buttonBiz2 = (Button) findViewById(R.id.buttonBiz2);
        buttonBiz3 = (Button) findViewById(R.id.buttonBiz3);
        buttonBiz4 = (Button) findViewById(R.id.buttonBiz4);
        imageViewBiz1 = (ImageView) findViewById(R.id.imageViewBiz1);
        imageViewBiz2 = (ImageView) findViewById(R.id.imageViewBiz2);
        imageViewBiz3 = (ImageView) findViewById(R.id.imageViewBiz3);
        imageViewBiz4 = (ImageView) findViewById(R.id.imageViewBiz4);
    }


    @Override
    public void onClick(View v) {

    }
}
