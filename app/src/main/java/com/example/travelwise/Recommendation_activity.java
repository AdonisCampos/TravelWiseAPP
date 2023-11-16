package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Recommendation_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        ImageButton imageb1 = findViewById(R.id.btnAnteCity);
        ImageButton imageb2 = findViewById(R.id.btnPrinCity);
        Button btnAddRecomendar = findViewById(R.id.btnRecomendarAdd);

        imageb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Recommendation_activity.this, City_Activity.class);
                startActivity(intent);
            }
        });
        imageb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Recommendation_activity.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
        btnAddRecomendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recommendation_activity.this,Add_recommendation.class);
                startActivity(intent);
            }
        });
    }
}