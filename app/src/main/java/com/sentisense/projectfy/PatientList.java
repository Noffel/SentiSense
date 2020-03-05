package com.sentisense.projectfy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sentisense.projectfy.Adapter.RVAdapter;
import com.sentisense.projectfy.Model.AddPatient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientList extends AppCompatActivity {
    Button logout,add;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    List<AddPatient> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter ;
    private FirebaseAuth mAuth;
    String text;
    TextView logouts, back;
    TextView about;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientList.this, home.class);
                startActivity(intent);
                finish();
            }
        });



        recyclerView=findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(PatientList.this));
        recyclerView.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        logout=findViewById(R.id.log);
       // add=findViewById(R.id.add);


//        about=findViewById(R.id.about);
//
//        about.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(PatientList.this,About.class);
//                startActivity(intent);
//            }
//        });




        logouts=findViewById(R.id.logouts);
        logouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PatientList.this,Start_page.class);
                startActivity(intent);
            }
        });









//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String i =getIntent().getStringExtra("docid");
//                Intent intent = new Intent(PatientList.this, AddingPatient.class);
//                intent.putExtra("doc",i);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//
//            }
//        });

        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        assert firebaseUser != null;
//        String id = firebaseUser.getUid();
        //String useremail=emailid.replace(".",",");
        reference= FirebaseDatabase.getInstance().getReference("AddedPatients");


    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ndataSnapshot:dataSnapshot.getChildren()) {

//                      //AddPatient patientDetails = ndataSnapshot.getValue(AddPatient.class);
                        AddPatient patientDetails = ndataSnapshot.getValue(AddPatient.class);
                        //text=ndataSnapshot.getValue(String.class);
                        list.add(patientDetails);
                    }
                    //Toast.makeText(PatientList.this, text, Toast.LENGTH_SHORT).show();

                    adapter = new RVAdapter(PatientList.this,list);
                    recyclerView.setAdapter(adapter);

                    //progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PatientList.this, "Oops... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
