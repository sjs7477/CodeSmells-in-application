package com.test.mvcnytimessample.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.test.mvcnytimessample.R;

public class ArticleDetails extends AppCompatActivity {
    private TextView itemDetail;
    private FloatingActionButton fab;
    private Toolbar mToolbar;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        itemDetail = findViewById(R.id.article_detail);
        fab = findViewById(R.id.fab);

        //toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // get data from previous activity
        Intent i = getIntent();
        if (i != null) {
            String detail = i.getStringExtra("detail");
            url = i.getStringExtra("url");
            itemDetail.setText(detail);
        }
    }

    //on fab click open url
    public void fabClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
