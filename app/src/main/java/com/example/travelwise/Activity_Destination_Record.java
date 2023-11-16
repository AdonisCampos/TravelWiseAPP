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

public class Activity_Destination_Record extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_record);
        Spinner spinnerPaises = findViewById(R.id.spinnerPaises);
        ImageButton imageBack = findViewById(R.id.btnAnt);
        ImageButton imageHome = findViewById(R.id.btnPri);
        Button btnContinuar = findViewById(R.id.btnRegistro);


        String []paises = {"El Salvador", "Guatemala", "Honduras", "Nicaragua", "Costa Rica", "Panamá"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerPaises.setAdapter(adapter);


        spinnerPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String selectedPais = paises[position];
                Toast.makeText(Activity_Destination_Record.this, "Seleccionaste: " + selectedPais, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Destination_Record.this, City_Activity.class);
                startActivity(intent);
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Destination_Record.this, Countries_Activity.class);
                startActivity(intent);
            }
        });
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Destination_Record.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
    }
    }
