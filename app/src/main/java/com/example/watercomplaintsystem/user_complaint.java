package com.example.watercomplaintsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_complaint extends AppCompatActivity {

    EditText text,phoneNo;
    Button btn,btn3;
    DatabaseReference reference;
    String name,phoneno,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complaint);

        text = findViewById(R.id.complainttext);
        btn = findViewById(R.id.button);
        name=getIntent().getStringExtra("name");
        phoneno=getIntent().getStringExtra("phoneNo");
        email=getIntent().getStringExtra("email");
        Toast.makeText(user_complaint.this, phoneno+"", Toast.LENGTH_SHORT).show();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("user").child(phoneno);
                //reference = rootNode.getReference("user");


                final String textview = text.getText().toString();

                reference.child("complaint").setValue(textview);
                Toast.makeText(user_complaint.this,"success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),comp_registered.class));

            }
        });



    }
}