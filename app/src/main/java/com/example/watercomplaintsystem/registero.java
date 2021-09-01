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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class registero extends AppCompatActivity {

    EditText mFullName,mEmail,mPassword,mphoneNo;
    Button mregisterbtn,mloginbtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registero);

        mFullName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mregisterbtn = findViewById(R.id.registerBtn);
        mloginbtn = findViewById(R.id.loginBtn);
        mphoneNo = findViewById(R.id.phone);

        fAuth =FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);

        if(getCurrentFocus()!=null){
            startActivity(new Intent(getApplicationContext(),officer_complaint.class));
            finish();

        }

        mregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Email = mEmail.getText().toString().trim();
                final String Password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password)){
                    mEmail.setError("Email  and password are required");
                }

                else if(Password.length()<4){
                    mPassword.setError("password must be at least 4 character long");
                }

                else {
                    progressBar.setVisibility(View.VISIBLE);

                    // register user in firebase

                    fAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(registero.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), officer_complaint.class));


                                rootNode = FirebaseDatabase.getInstance();
                                reference = rootNode.getReference("officer");

                                String phoneNo = mphoneNo.getText().toString();
                                String name = mFullName.getText().toString();
                                String email = mEmail.getText().toString();
                                String password = mPassword.getText().toString();

                                HashMap hashmap=new HashMap<>();
                                hashmap.put("phoneNo",phoneNo);
                                hashmap.put("name",name);
                                hashmap.put("email",email);
                                hashmap.put("password",password);
                             //   UserHelperClass helperClass = new UserHelperClass(phoneNo,name,email,password);

                                reference.child(phoneNo).setValue(hashmap);


                            } else {
                                Toast.makeText(registero.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }


                    });
                }


            }
        });

        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Logino.class));
            }
        });


    }
}

