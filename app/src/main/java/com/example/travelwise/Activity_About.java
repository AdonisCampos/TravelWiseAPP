package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Activity_About extends AppCompatActivity {
     ImageButton homee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        homee = findViewById(R.id.btnhomee);

        homee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Activity_About.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
    }
}