package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Activity_Modify_Recommendation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_recommendation);
        ImageButton imageB11 = findViewById(R.id.imageButtonPrincipal);
        ImageButton imageA12 = findViewById(R.id.imageButtonRetornar);

        imageA12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Modify_Recommendation.this, Recommendations_add_and_cities_activity.class);
                startActivity(intent);
            }
        });
        imageB11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Modify_Recommendation.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });

        Spinner spinner = findViewById(R.id.SpinnerEditarRecomendar);
        String[] recommendationsArray = getResources().getStringArray(R.array.recomendar);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, recommendationsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }
    }
