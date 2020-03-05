package com.sentisense.projectfy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {
    Button question,history,status,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        question=findViewById(R.id.question);
        history=findViewById(R.id.history);
        status=findViewById(R.id.status);
        back=findViewById(R.id.back);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=getIntent().getStringExtra("patid");
                Intent intent = new Intent(Choose.this, CheckupQuestions.class);
                intent.putExtra("patname",a);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=getIntent().getStringExtra("patid");
                Intent intent = new Intent(Choose.this, PatientHistory.class);
                intent.putExtra("patname",a);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose.this, predict_status.class);
                startActivity(intent);
                

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose.this, patientInfo.class);
                startActivity(intent);


            }
        });


    }
}
