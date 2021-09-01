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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {

    EditText mFullName,mEmail,mPassword,mphoneNo;
    Button mregisterbtn,mloginbtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mregisterbtn = findViewById(R.id.registerBtn);
        mloginbtn = findViewById(R.id.loginBtn);
        mphoneNo = findViewById(R.id.phone);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);

        if (getCurrentFocus() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }

        mregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Email = mEmail.getText().toString().trim();
                final String Password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
                    mEmail.setError("Email  and password are required");
                } else if (Password.length() < 4) {
                    mPassword.setError("password must be at least 4 character long");
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    // register user in firebase

                    fAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Login.class));


                                rootNode = FirebaseDatabase.getInstance();
                                reference = rootNode.getReference("user");

                                String phoneNo = mphoneNo.getText().toString();
                                String name = mFullName.getText().toString();
                                String email = mEmail.getText().toString();
                                String password = mPassword.getText().toString();
                                String x="";
                                UserHelperClass helperClass = new UserHelperClass(phoneNo, name, email, password,x);

                                reference.child(phoneNo).setValue(helperClass);


                            } else {
                                Toast.makeText(register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }


                    });
                }


            }
        });

        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });







        }
    }