package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Add_recommendation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recommendation);
        ImageButton imageb3 = findViewById(R.id.btnAnt);
        ImageButton imageb4 = findViewById(R.id.btnPri);


        imageb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Add_recommendation.this, Recommendation_activity.class);
                startActivity(intent);
            }
        });
        imageb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_recommendation.this,Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
    }
}