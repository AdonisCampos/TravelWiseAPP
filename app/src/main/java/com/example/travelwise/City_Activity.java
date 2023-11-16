package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class City_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        Spinner spinnerCiudades = findViewById(R.id.SpinnerCiudades);
        ImageButton imagereturn = findViewById(R.id.btnAnt);
        ImageButton imageHome = findViewById(R.id.btnPri);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnAddCity = findViewById(R.id.btnAddCity);

        String[] ciudades = {
                "Ahuachapán", "Sensuntepeque", "Chalatenango", "Cojutepeque", "Santa Tecla",
                "Zacatecoluca", "La Unión", "San Francisco Gotera", "San Miguel", "San Salvador",
                "San Vicente", "Santa Ana", "Sonsonate", "Usulután"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,ciudades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerCiudades.setAdapter(adapter);


        spinnerCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String selectedPais = ciudades[position];
                Toast.makeText(City_Activity.this, "Seleccionaste: " + selectedPais, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(City_Activity.this, Recommendation_activity.class);
                startActivity(intent);
            }
        });
        imagereturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(City_Activity.this, Activity_Destination_Record.class);
                startActivity(intent);
            }
        });
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(City_Activity.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(City_Activity.this, Schedule_city_activity.class);
                startActivity(intent);
            }
        });
    }
}