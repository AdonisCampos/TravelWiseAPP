package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Schedule_city_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_city);
        ImageButton imageA7 = findViewById(R.id.btnAnteCity);
        ImageButton imageB8 = findViewById(R.id.btnPrinCity);

        imageA7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Schedule_city_activity.this, City_Activity.class);
                startActivity(intent);
            }
        });
        imageB8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Schedule_city_activity.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
    }
}