package com.sentisense.projectfy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class Start_page extends AppCompatActivity {


    CircleImageView doctor;
    CircleImageView patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);



        doctor = (CircleImageView) findViewById(R.id.pic1);

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(Start_page.this,Login.class);
                startActivity(log);
            }
        });

        patient = (CircleImageView) findViewById(R.id.pic2);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(Start_page.this,PatientLogin.class);
                startActivity(log);
            }
        });
    }
}

