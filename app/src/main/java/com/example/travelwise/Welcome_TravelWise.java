package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome_TravelWise extends AppCompatActivity {
    ImageView seleccionarPais, cerrar, editarperfil,info,lugarP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_travel_wise);
        seleccionarPais = findViewById(R.id.iconoExplorar);
        cerrar= findViewById(R.id.iconoCerrarSesion);
        editarperfil= findViewById(R.id.iconoProfile);
        info= findViewById(R.id.acercade);
        lugarP= findViewById(R.id.LugaresPopulares);
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
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent = new Intent( Welcome_TravelWise.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        editarperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Welcome_TravelWise.this, Activity_Profile_Edit.class);
                startActivity(intent);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Welcome_TravelWise.this, Activity_About.class);
                startActivity(intent);
            }
        });

        lugarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Welcome_TravelWise.this, Activity_Popular_Places.class);
                startActivity(intent);
            }
        });
    }
}