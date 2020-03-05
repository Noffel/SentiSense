package com.sentisense.projectfy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tweet extends AppCompatActivity {
    Button fetch,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        fetch = findViewById(R.id.status);
        back = findViewById(R.id.back);

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tweet.this, predict_status.class);
                startActivity(intent);
            }


        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tweet.this, Choose.class);
                startActivity(intent);
            }

        });


    }
}
