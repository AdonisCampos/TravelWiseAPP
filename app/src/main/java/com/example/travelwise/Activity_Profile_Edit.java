package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Activity_Profile_Edit extends AppCompatActivity {
    ImageButton imagePin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        imagePin = findViewById(R.id.btnP);
        imagePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Profile_Edit.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });

    }

}