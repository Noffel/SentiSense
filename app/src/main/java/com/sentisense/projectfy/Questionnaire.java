package com.sentisense.projectfy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sentisense.projectfy.Model.Questions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Questionnaire extends AppCompatActivity {


    EditText bring,viewpoint,wave,counselreviews,reviews;
    Button done;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);


        bring=findViewById(R.id.bring);
        viewpoint=findViewById(R.id.viewpoint);
        wave=findViewById(R.id.wave);
        counselreviews=findViewById(R.id.counsel);
        reviews=findViewById(R.id.addreview);
        done=findViewById(R.id.done);
        mAuth = FirebaseAuth.getInstance();


        databaseReference= FirebaseDatabase.getInstance().getReference("Questions");
        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String i=getIntent().getStringExtra("patid");
                final String bringing=bring.getText().toString();
                final String review=viewpoint.getText().toString();
                final String waving=wave.getText().toString();
                final String counseling=counselreviews.getText().toString();
                final String reviewing=reviews.getText().toString();
                final String docname=i;
                if (TextUtils.isEmpty(bringing) || TextUtils.isEmpty(review) || TextUtils.isEmpty(waving) || TextUtils.isEmpty(counseling)|| TextUtils.isEmpty(reviewing)) {
                    Toast.makeText(Questionnaire.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    assert firebaseUser != null;
                    //String id = firebaseUser.getUid();
                    //String useremail=emailid.replace(".",",");
                    String userid = databaseReference.push().getKey();
                    Questions questions = new Questions();

                    questions.setId(userid);
                    questions.setBring(bringing);
                    questions.setCounselreviews(counseling);
                    questions.setViewpoint(review);
                    questions.setWave(waving);
                    questions.setPatname(docname);

                    databaseReference.child(userid).setValue(questions).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent = new Intent(Questionnaire.this, predict_status.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            //Toast.makeText(AddingPatient.this, "Success", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(AddingPatient.this, username, Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    });
                }

            }
        });



    }
}
