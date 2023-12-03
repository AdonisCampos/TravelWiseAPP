package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recommendations_add_and_cities_activity extends AppCompatActivity {
    ListView listViewCiudades;
    ListView listViewRecomendaciones;
//URL PARA CIUDAD = http://192.168.1.2/travelwise/destino_ciudad_listview.php
    //URL PARA RECOMENDACION = http://192.168.1.2/travelwise/destino_recomendacion_listview.php
    String[] ciudades = {"Ciudad 1", "Ciudad 2", "Ciudad 3"};
    String[] recomendaciones = {"Recomendación 1", "Recomendación 2", "Recomendación 3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations_add_and_cities);

        setContentView(R.layout.activity_recommendations_add_and_cities);

        Intent intent = getIntent();
        int selectedIdDestino = intent.getIntExtra("id_destino", 0); // 0 es un valor por defecto si no se encuentra el ID


        listViewCiudades = findViewById(R.id.listViewCiudad);
        listViewRecomendaciones = findViewById(R.id.listViewRecomendar);
        ImageButton imageB9 = findViewById(R.id.btnPrinCity);
        ImageButton imageA10 = findViewById(R.id.btnAnteCity);

        ArrayAdapter<String> ciudadesAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.textViewItem, ciudades);
        ArrayAdapter<String> recomendacionesAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.textViewItem, recomendaciones);
        listViewCiudades.setAdapter(ciudadesAdapter);
        listViewRecomendaciones.setAdapter(recomendacionesAdapter);


        // URL para obtener datos de ciudades
        String urlCiudades = "http://192.168.1.2/travelwise/destino_ciudad_listview.php";

        // URL para obtener datos de recomendaciones
        String urlRecomendaciones = "http://192.168.1.2/travelwise/destino_recomendacion_listview.php";

        // Obtener datos de ciudades y cargar en el ListView
        obtenerDatosCiudades(urlCiudades, listViewCiudades, selectedIdDestino);

        // Obtener datos de recomendaciones y cargar en el ListView
        obtenerDatosRecomendaciones(urlRecomendaciones, listViewRecomendaciones,selectedIdDestino);

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
    private void obtenerDatosCiudades(String urlCiudades, ListView listViewCiudades, int selectedIdDestino) {
        String URL = urlCiudades + "?id_destino=" + selectedIdDestino;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    List<String> items = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String nombreCiudad = jsonObject.getString("nombre_ciudad");

                        items.add(nombreCiudad);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Recommendations_add_and_cities_activity.this,
                            R.layout.list_item, R.id.textViewItem, items);

                    listViewCiudades.setAdapter(adapter);

                    // Agrega aquí el setOnItemClickListener si deseas manejar clics en los elementos del ListView
                    listViewCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Lógica para manejar el clic en el elemento del ListView
                            // Puedes obtener el elemento seleccionado usando "items.get(position)"
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Recommendations_add_and_cities_activity.this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Recommendations_add_and_cities_activity.this,
                        "Error al obtener datos de la URL de ciudades: " + urlCiudades, Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void obtenerDatosRecomendaciones(String urlRecomendaciones, ListView listViewRecomendaciones, int selectedIdDestino) {
        String URL = urlRecomendaciones + "?id_destino=" + selectedIdDestino;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    List<String> items = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String tituloRecomendacion = jsonObject.getString("titulo_recomendacion");

                        items.add(tituloRecomendacion);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Recommendations_add_and_cities_activity.this,
                            R.layout.list_item, R.id.textViewItem, items);

                    listViewRecomendaciones.setAdapter(adapter);

                    // Agrega aquí el setOnItemClickListener si deseas manejar clics en los elementos del ListView
                    listViewRecomendaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Lógica para manejar el clic en el elemento del ListView
                            // Puedes obtener el elemento seleccionado usando "items.get(position)"
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Recommendations_add_and_cities_activity.this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Recommendations_add_and_cities_activity.this,
                        "Error al obtener datos de la URL de recomendaciones: " + urlRecomendaciones, Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}