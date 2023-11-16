package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class Countries_Activity extends AppCompatActivity {

  ListView paises;
  String []paisesNombre = {"El Salvador", "Guatemala", "Honduras", "Nicaragua", "Costa Rica", "Panamá"};
    String []paisesDescripcion = {"Un pais magico", "Un pais con cultura",
            "El Turistico", "Pais Cafetalero", "Pais en desarrollo", "Pais con exportaciones e importacioes"};
    int []paisesImagenes  =
            {
                    R.drawable.elsalvador,
                    R.drawable.guatemala,
                    R.drawable. honduras,
                    R.drawable.nicaragua,
                    R.drawable.costarica,
                    R.drawable.panama,

             };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        paises = findViewById(R.id.paisesListView);
        PaisesAdapter paisesAdapter = new PaisesAdapter(this, paisesNombre, paisesImagenes,paisesDescripcion);
        paises.setAdapter(paisesAdapter);

        ImageButton imageButtonMore = findViewById(R.id.imagemore);
        ImageButton imageButtonback = findViewById(R.id.backHome);
        ImageButton imageButtonhome = findViewById(R.id.btnAnt);
        imageButtonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Countries_Activity.this, Activity_Destination_Record.class);
                startActivity(intent);
            }
        });
        imageButtonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Countries_Activity.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
        imageButtonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Countries_Activity.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
    }
    }
