package com.example.watercomplaintsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button mloginbtn;
    Button mregisterbtn;
    EditText mphoneNo, Password;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mloginbtn = findViewById(R.id.loginBtn);
        mregisterbtn = findViewById(R.id.registerBtn);
        mphoneNo = findViewById(R.id.phone);
        Password = findViewById(R.id.password);

        mregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), register.class));
            }
        });


        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEnteredphoneNo = mphoneNo.getText().toString().trim();
                final String userEnteredPassword = Password.getText().toString().trim();
                if (!(TextUtils.isEmpty(userEnteredphoneNo) || TextUtils.isEmpty(userEnteredPassword))){
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user").child(userEnteredphoneNo);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String passwordFromDB = snapshot.child("password").getValue(String.class);

                        if (passwordFromDB.equals(userEnteredPassword)) {

                            String nameFromDB = snapshot.child("name").getValue(String.class);
                            String phoneNoFromDB = snapshot.child("phoneNo").getValue(String.class);
                            String emailFromDB = snapshot.child("email").getValue(String.class);

                            Intent intent = new Intent(Login.this, user_complaint.class);

                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("phoneNo", phoneNoFromDB);
                            intent.putExtra("email", emailFromDB);

                            startActivity(intent);
                        } else {
                            Password.setError("Wrong Password");
                           // Password.requestFocus();
                        }
                        //   }
                        //   else {
                        //      mphoneNo.setError("No Such User Exists");
                        //  }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }else{
                    Toast.makeText(Login.this,"Please enter all details",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

//                private Boolean validatePhoneNo () {
//                    String val = mphoneNo.getText().toString();
//
//                    if (val.isEmpty()) {
//                        mphoneNo.setError("Field cannot be empty");
//                        return false;
//                    } else {
//                        mphoneNo.setError(null);
//                        return true;
//                    }
//
//
//                }
//
//                private Boolean validatePassword () {
//                    String val = Password.getText().toString();
//
//                    if (val.isEmpty()) {
//                        Password.setError("Field cannot be empty");
//                        return false;
//                    } else {
//                        Password.setError(null);
//                        return true;
//                    }
//
//                }
//
//                public void loginUser (){
//                    //validate login Info
//                    if (!validatePhoneNo() || !validatePassword()) {
//                        return;
//                    } else {
//                        isUser();
//                    }
//                }
//
//                private void isUser(){
//
//                }

    }


