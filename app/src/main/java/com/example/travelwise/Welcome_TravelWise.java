package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome_TravelWise extends AppCompatActivity {
    ImageView seleccionarPais, cerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_travel_wise);
        seleccionarPais = findViewById(R.id.iconoExplorar);
        cerrar= findViewById(R.id.iconoCerrarSesion);
        seleccionarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Welcome_TravelWise.this, Countries_Activity.class);
                startActivity(intent);
            }
        });
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Welcome_TravelWise.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}