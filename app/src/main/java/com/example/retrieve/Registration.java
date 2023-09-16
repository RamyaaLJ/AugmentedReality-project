package com.example.retrieve;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    EditText registeremail,registername,registerpassword,registerconfirm;
    Button registerbutton;
    TextView login;
    FirebaseAuth fAuth;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root=db.getReference().child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        login= (TextView) findViewById(R.id.textView4);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });
        registeremail = findViewById(R.id.registeremail);
        registername = findViewById(R.id.registername);
        registerpassword = findViewById(R.id.registerpassword);
        registerconfirm = findViewById(R.id.registerconfirm);
        registerbutton = findViewById(R.id.button2);
        fAuth=FirebaseAuth.getInstance();
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String t1=registeremail.getText().toString();
                final String t2=registername.getText().toString();
                final String t3=registerpassword.getText().toString();
                final String t5=registerconfirm.getText().toString();

                HashMap<String,String> userMap=new HashMap<>();
                userMap.put("email",t1);
                userMap.put("name",t2);
                userMap.put("password",t3);
                root.push().setValue(userMap);
                if (TextUtils.isEmpty(t1)) {
                    registeremail.setError("Email is required!");
                    return;
                }
                if (TextUtils.isEmpty(t2)) {
                    registername.setError("Name is required!");
                    return;
                }
                if (TextUtils.isEmpty(t3)) {
                    registerpassword.setError("Password is required!");
                    return;
                }
                if (TextUtils.isEmpty(t5)) {
                    registerconfirm.setError("Confirm Password is required!");
                    return;
                }
                if(!t3.equals(t5)){
                    registerconfirm.setError("Password does not match");
                    return;
                }



                fAuth.createUserWithEmailAndPassword(t1,t3).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>(){
                                public void onComplete(@NonNull Task<Void> task){
                                    if(task.isSuccessful()){
                                        Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Registration.this,Login.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registration.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}