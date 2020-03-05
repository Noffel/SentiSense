package com.sentisense.projectfy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class home extends AppCompatActivity {

    LinearLayout patients;
    LinearLayout add;
    LinearLayout info;
    LinearLayout visit;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        add=findViewById(R.id.add);
        visit=findViewById(R.id.visit);
        patients=findViewById(R.id.patients);
        info=findViewById(R.id.info);
        logout=findViewById(R.id.logout);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i=getIntent().getStringExtra("doctor_icon");
                Intent log = new Intent(home.this, AddingPatient.class);
                log.putExtra("docid",i);
                startActivity(log);
            }
        });


        patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i=getIntent().getStringExtra("doctor_icon");
                Intent log = new Intent(home.this, PatientList.class);
                log.putExtra("docid",i);
                startActivity(log);
            }
        });





        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(home.this, CheckupQuestions.class);
                startActivity(log);
            }
        });




        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(home.this, About.class);
                startActivity(log);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(home.this, MainActivity.class);
                startActivity(log);
            }
        });






    }
}
