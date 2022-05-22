package com.example.firebaseauthwithrealtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText pswd;
    Button regbtn;
    FirebaseDatabase fb;
    DatabaseReference dr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pswd = findViewById(R.id.pswd);
        regbtn = findViewById(R.id.button);
        fb = FirebaseDatabase.getInstance();
        dr = fb.getReference().child("User");


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            User userdata = new User(name.getText().toString(),email.getText().toString(),pswd.getText().toString());
            dr.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(email.getText().toString().substring(0,(email.getText().toString().indexOf(".")))).exists())
                    {
                        Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        try{
                            dr.child(email.getText().toString().substring(0,(email.getText().toString().indexOf(".")))).setValue(userdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "User Successfully Added", Toast.LENGTH_SHORT).show();
                                    name.setText("");
                                    email.setText("");
                                    pswd.setText("");
                                }
                            });
                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




            }
        });

        TextView loginlink =findViewById(R.id.textView4);
        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

    }
}