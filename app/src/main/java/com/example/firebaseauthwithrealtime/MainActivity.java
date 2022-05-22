package com.example.firebaseauthwithrealtime;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String n = getIntent().getStringExtra("name");
        int i = n.length();
        n = "Hello There, " + n.substring(0,1).toUpperCase() + n.substring(1,i) + "\n" + "This You Dashbpoard\nSeems Pretty Empty Right ?\nToh Kar na! Develop Thak Gaye Kabse hum";
        TextView tv = findViewById(R.id.tex);
        tv.setText(n);

    }
}