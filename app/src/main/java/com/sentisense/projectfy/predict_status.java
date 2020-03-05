package com.sentisense.projectfy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import com.sentisense.projectfy.Model.StatusModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class predict_status extends AppCompatActivity {

    TextView label;
    TextView intensity;
    EditText msg;
    Button  back, predict;
    DatabaseReference databaseReference;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_status);

        predict=findViewById(R.id.predict);
        msg = findViewById(R.id.msg);
        label = findViewById(R.id.label);
        intensity = findViewById(R.id.intensity);
        back=findViewById(R.id.back);


        databaseReference= FirebaseDatabase.getInstance().getReference("Status");

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject obj=new JSONObject();
                try {
                    obj.put("Sentence",msg.getText().toString()+"");
                    sendMessage(obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(predict_status.this, home.class);
                startActivity(intent);
            }
        });



    }
    private void sendMessage(final String msg) {
        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket("192.168.100.68", 5000);
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (st.trim().length() != 0) {
                                try {
                                    JSONObject obj=new JSONObject(st);
                                    String labell=obj.getString("label");
                                    String label1="";
                                    String inten="";
                                    StringTokenizer st= new StringTokenizer(labell,",");

                                    for (int i=0;st.hasMoreTokens();i++)
                                    {
                                        if (i==0)
                                        {
                                            label1=st.nextToken();
                                        }
                                        else
                                        {
                                            inten=st.nextToken();
                                        }
                                    }

                                    //String inten=obj.getString("intensity");
                                    //label1=String.valueOf(label1);
                                    //inten=String.valueOf(inten);
                                    Log.d("Labelss", label1);
                                    Log.d("intensity", inten);
                                    if(label1.equals("0"))
                                    {
                                        label.setText("Anger");
                                        intensity.setText(inten);
                                    }
                                    else if (label1.equals("1"))
                                    {
                                        label.setText("Fear1");
                                        intensity.setText(inten);
                                    }
                                    else if (label1.matches("2"))
                                    {
                                        label.setText("Joy");
                                        intensity.setText(inten);
                                    }
                                    else if (label1.matches("3"))
                                    {
                                        label.setText("Sad");
                                        intensity.setText(inten);
                                    }

                                    else
                                    {
                                        Toast.makeText(predict_status.this, "Error bro", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    output.close();
                    out.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
