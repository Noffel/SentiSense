package com.sentisense.projectfy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class CheckupQuestions extends AppCompatActivity {

    EditText bring,viewpoint,wave,counselreviews,reviews;
    Button proceed;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkup_questions);

        bring=findViewById(R.id.bring);
        viewpoint=findViewById(R.id.viewpoint);
        wave=findViewById(R.id.wave);
        counselreviews=findViewById(R.id.counsel);
        reviews=findViewById(R.id.addreview);
        proceed=findViewById(R.id.proceed);
        mAuth = FirebaseAuth.getInstance();


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckupQuestions.this, Questionnaire.class);
                startActivity(intent);
            }
        });





    }
}
