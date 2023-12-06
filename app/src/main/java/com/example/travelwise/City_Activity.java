package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City_Activity extends AppCompatActivity {
    private Spinner spinnerCiudades;
    private Integer selectedCountryID;
    ImageButton imageBack;
    ImageButton imageHome;

    ListView ciudadesListView;
    Button addCity ,btnRecomendation;
    private List<Map<String, String>> ciudadesData;
    private List<String> ciudadesSeleccionadas;
    private ArrayAdapter<String> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        btnRecomendation = findViewById(R.id.btnNext);
        addCity = findViewById(R.id.btnAddCity);
        ciudadesListView = findViewById(R.id.ListviewCiudades);
        spinnerCiudades = findViewById(R.id.SpinnerCiudades);
        imageHome = findViewById(R.id.btnPrinCity);
        imageBack = findViewById(R.id.btnAnteCity);
        ciudadesData = new ArrayList<>();
        ciudadesSeleccionadas = new ArrayList<>();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selected_country_id")) {
            selectedCountryID = intent.getIntExtra("selected_country_id", -1);
            if (selectedCountryID != -1) {
                obtenerDatosCiudades(selectedCountryID);
            } else {
                Toast.makeText(this, "ID del país no válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ID del país no recibido", Toast.LENGTH_SHORT).show();
        }

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = spinnerCiudades.getSelectedItemPosition();
                if (position != AdapterView.INVALID_POSITION) {
                    Map<String, String> selectedCityData = ciudadesData.get(position);
                    String selectedCityName = selectedCityData.get("nombre_ciudad");
                    ciudadesSeleccionadas.add(selectedCityName);
                    actualizarListView();
                } else {
                    Toast.makeText(City_Activity.this, "No se ha seleccionado ninguna ciudad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
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

        btnRecomendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ciudadesSeleccionadas.isEmpty()) {
                    enviarDatosARecommendationActivity();
                } else {
                    Toast.makeText(City_Activity.this, "No hay ciudades seleccionadas", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void enviarDatosARecommendationActivity() {
        Intent intent = new Intent(City_Activity.this, Add_recommendation.class);

        // Pasar el ID del país al que pertenecen las ciudades seleccionadas
        intent.putExtra("selected_country_id", selectedCountryID);

        // Pasar la lista de ciudades seleccionadas
        intent.putStringArrayListExtra("selected_cities_list", (ArrayList<String>) ciudadesSeleccionadas);

        startActivity(intent);
    }

    private void obtenerDatosCiudades(Integer selectedCountryID) {
        String URL = "http://192.168.1.2/travelwise/ciudad_listview.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<String> ciudadesList = new ArrayList<>();
                ciudadesData.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String nombreCiudad = jsonObject.getString("nombre_ciudad");
                        ciudadesList.add(nombreCiudad);

                        Map<String, String> cityData = new HashMap<>();
                        cityData.put("id", jsonObject.getString("id"));
                        cityData.put("nombre_ciudad", nombreCiudad);
                        ciudadesData.add(cityData);
                    }

                    setupSpinnerAdapter(ciudadesList);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(City_Activity.this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(City_Activity.this, "Error al obtener datos de ciudades: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_pais", String.valueOf(selectedCountryID));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setupSpinnerAdapter(List<String> ciudadesList) {
        ArrayAdapter<String> ciudadesAdapter = new ArrayAdapter<>(
                City_Activity.this,
                android.R.layout.simple_spinner_item,
                ciudadesList
        );
        ciudadesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiudades.setAdapter(ciudadesAdapter);
    }

    private CiudadAdapter ciudadAdapter;

    private void actualizarListView() {
        if (ciudadAdapter == null) {
            ciudadAdapter = new CiudadAdapter(this, ciudadesSeleccionadas);
            ciudadesListView.setAdapter(ciudadAdapter);
        } else {
            ciudadAdapter.notifyDataSetChanged();
        }
    }
}
