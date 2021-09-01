package com.example.watercomplaintsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Logino extends AppCompatActivity {

    EditText phoneNo,Password;
    Button btn1,btn2;
    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logino);
        auth=FirebaseAuth.getInstance();
        phoneNo = findViewById(R.id.phone);
        Password = findViewById(R.id.password);
        btn1 = findViewById(R.id.loginBtn);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Signing in..");
                dialog.setMessage("Please wait...");
    //    btn2 = findViewById(R.id.registerBtn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                final String phone=phoneNo.getText().toString();
                final String pass=Password.getText().toString();
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("officer");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String pass1=snapshot.child(phone).child("password").getValue(String.class);
                            if(pass.equals(pass1)){
                              Toast.makeText(Logino.this,"Authentication Successful",Toast.LENGTH_SHORT).show();
                              dialog.dismiss();
                              Intent intent=new Intent(Logino.this,officer_complaint.class);
                              startActivity(intent);
                            }
                            else{
                                 Toast.makeText(Logino.this,"Passowrd is incorrect.",Toast.LENGTH_SHORT).show();
                                 dialog.dismiss();
                            }
                        }
                        else{
                            Toast.makeText(Logino.this,"This is not correct detail",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
               // startActivity(new Intent(getApplicationContext(), officer_complaint.class));
            }
        });

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), registero.class));
//            }
//        });
    }
}