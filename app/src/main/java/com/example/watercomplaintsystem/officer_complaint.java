package com.example.watercomplaintsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class officer_complaint extends AppCompatActivity {

    TextView name,phoneNo,Email,complaint;
    DatabaseReference reference;
    RecyclerView recyclerView;
    Query query;
    Button btn12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_complaint);
       // recyclerView=findViewById(R.id.complaints);
        name = findViewById(R.id.Name);
        phoneNo = findViewById(R.id.phone);
        Email = findViewById(R.id.email);
        complaint = findViewById(R.id.Complaint);
        btn12 = findViewById(R.id.buttonH);

        //show all data
        showAllUserData();
    }

    private void showAllUserData() {
       // Intent intent =getIntent();
        reference= FirebaseDatabase.getInstance().getReference("user");
 //       query=reference.limitToLast(1);
        reference.child("12345").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1=snapshot.child("name").getValue(String.class);
                String email1=snapshot.child("email").getValue(String.class);
                String phone12=snapshot.child("phoneNo").getValue(String.class);
                String comp=snapshot.child("complaint").getValue(String.class);
                complaint.setText(comp);
                name.setText(name1);
                Email.setText(email1);
                phoneNo.setText(phone12);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}