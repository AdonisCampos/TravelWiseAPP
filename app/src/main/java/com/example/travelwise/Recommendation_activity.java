package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

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

                finish();
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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selected_country_id") && intent.hasExtra("selected_cities_list")) {
            int selectedCountryID = intent.getIntExtra("selected_country_id", -1);
            ArrayList<String> selectedCitiesList = intent.getStringArrayListExtra("selected_cities_list");

        }

    }
}