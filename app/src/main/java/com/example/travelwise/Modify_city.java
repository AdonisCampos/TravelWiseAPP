package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Modify_city extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_city);
        ImageButton imageB11 = findViewById(R.id.imageButtonBack);
        ImageButton imageA12 = findViewById(R.id.imageButtonHome);

        imageB11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Modify_city.this, Recommendations_add_and_cities_activity.class);
                startActivity(intent);
            }
        });
        imageA12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Modify_city.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
    }
}