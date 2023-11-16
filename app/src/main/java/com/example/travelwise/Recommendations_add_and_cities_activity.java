package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class Recommendations_add_and_cities_activity extends AppCompatActivity {
    ListView listViewCiudades;
    ListView listViewRecomendaciones;

    String[] ciudades = {"Ciudad 1", "Ciudad 2", "Ciudad 3"};  // Reemplaza con tus datos reales
    String[] recomendaciones = {"Recomendación 1", "Recomendación 2", "Recomendación 3"};  // Reemplaza con tus datos reales
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations_add_and_cities);

        listViewCiudades = findViewById(R.id.listViewCiudad);
        listViewRecomendaciones = findViewById(R.id.listViewRecomendar);
        ImageButton imageB9 = findViewById(R.id.btnPrinCity);
        ImageButton imageA10 = findViewById(R.id.btnAnteCity);

        ArrayAdapter<String> ciudadesAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.textViewItem, ciudades);
        ArrayAdapter<String> recomendacionesAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.textViewItem, recomendaciones);
        listViewCiudades.setAdapter(ciudadesAdapter);
        listViewRecomendaciones.setAdapter(recomendacionesAdapter);


        listViewCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedCity = ciudades[position];

                Intent intent = new Intent(Recommendations_add_and_cities_activity.this, Modify_city.class);
                intent.putExtra("city_name", selectedCity);
                startActivity(intent);
            }
        });

        listViewRecomendaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedRecommend = recomendaciones[position];
                Intent intent = new Intent(Recommendations_add_and_cities_activity.this, Activity_Modify_Recommendation.class);
                intent.putExtra("city_name", selectedRecommend);
                startActivity(intent);
            }
        });
        imageA10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Recommendations_add_and_cities_activity.this, Countries_Activity.class);
                startActivity(intent);
            }
        });
        imageB9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Recommendations_add_and_cities_activity.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
    }

}