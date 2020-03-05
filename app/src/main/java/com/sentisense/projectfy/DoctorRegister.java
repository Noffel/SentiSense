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

import com.sentisense.projectfy.Model.Doctor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorRegister extends AppCompatActivity {

    EditText name, email, password, phno;
    Button signup,back;

    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        phno = findViewById(R.id.phno);
        signup = findViewById(R.id.signup);
        back=findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = name.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_phno = phno.getText().toString();
                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_phno)) {
                    Toast.makeText(DoctorRegister.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 5) {
                    Toast.makeText(DoctorRegister.this, "password must be at least 5 characters", Toast.LENGTH_SHORT).show();
                } else {
                    register(txt_username, txt_email, txt_password, txt_phno);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DoctorRegister.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    public void register(final String username, final String email, String password, final String phno) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser firebaseUser=mAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String userid = firebaseUser.getUid();

                    Doctor doctor=new Doctor(
                            userid,
                            username,
                            phno,
                            email
                    );

                    FirebaseDatabase.getInstance().getReference("Doctors").child(userid).setValue(doctor).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(DoctorRegister.this, PatientList.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                //display a failure message
                            }
                        }
                    });

                }
                else {
                    FirebaseAuthException e = (FirebaseAuthException )task.getException();
                    Toast.makeText(DoctorRegister.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    //message.hide();
                    //return;
                    //Toast.makeText(DoctorRegister.this, "You can't register with this email or password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    protected void onDestroy() {
        FirebaseAuth.getInstance().signOut();
        super.onDestroy();
    }
}
