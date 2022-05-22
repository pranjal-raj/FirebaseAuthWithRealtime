package com.example.firebaseauthwithrealtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText name;
    EditText email_log;
    EditText pswd;
    Button logbtn;
    FirebaseDatabase fb;
    DatabaseReference dr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_log = findViewById(R.id.email_login);
        pswd = findViewById(R.id.pswd_login);
        logbtn = findViewById(R.id.button_login);
        fb = FirebaseDatabase.getInstance();
        dr = fb.getReference().child("User");


        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               try{

                   dr.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(email_log.getText().toString().indexOf(".")!=-1) {
                               if (snapshot.child(email_log.getText().toString().substring(0, (email_log.getText().toString().indexOf(".")))).exists()) {
                                   if (pswd.getText().toString().equals(snapshot.child(email_log.getText().toString().substring(0, (email_log.getText().toString().indexOf(".")))).child("pswd").getValue().toString())) {
                                       Intent i = new Intent(Login.this, MainActivity.class);
                                       String name = snapshot.child(email_log.getText().toString().substring(0, (email_log.getText().toString().indexOf(".")))).child("name").getValue().toString();
                                       i.putExtra("name", name);
                                       startActivity(i);
                                   } else {
                                       Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                                   }
                               }
                               else {
                                   Toast.makeText(Login.this, "Account Not Found Create A New One", Toast.LENGTH_SHORT).show();
                               }
                           }
                           else
                           {
                               Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });


                   /*dr.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                        try {
                            String s = snapshot.child("pranjal@gmail").getValue(User.class).getEmail();
                            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if(snapshot.hasChild(email_log.getText().toString().substring(0,(email_log.getText().toString().indexOf(".")))))
                        {
                            Toast.makeText(getApplicationContext(), "" , Toast.LENGTH_SHORT).show();
                            if(pswd.getText().toString().equals(snapshot.child(email_log.getText().toString().substring(0,(email_log.getText().toString().indexOf(".")))).child("pswd").getValue().toString()))
                            {
                                Intent i = new Intent(Login.this, MainActivity.class);
                                String name = snapshot.child(email_log.getText().toString().substring(0,(email_log.getText().toString().indexOf(".")))).child("pswd").getValue().toString();
                                i.putExtra("name",name);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }); */
               }
               catch(Exception e)
               {
                   Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
               }
            }
        });

    }
}