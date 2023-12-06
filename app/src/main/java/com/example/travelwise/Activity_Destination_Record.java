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

public class Activity_Destination_Record extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_record);

        Spinner spinnerPaises = findViewById(R.id.spinnerPaises);
        ImageButton imageBack = findViewById(R.id.btnAnteCity);
        ImageButton imageHome = findViewById(R.id.btnPrinCity);
        Button btnContinuar = findViewById(R.id.btnRegistro);


        String URL_Paises = "http://192.168.1.2/travelwise/paises_listview.php"; // Reemplaza con tu URL correcta


        StringRequest stringRequestPaises = new StringRequest(Request.Method.POST, URL_Paises, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<String> paisesList = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String nombrePais = jsonObject.getString("nombre_pais");
                        paisesList.add(nombrePais);
                    }

                    ArrayAdapter<String> adapterPaises = new ArrayAdapter<>(Activity_Destination_Record.this, android.R.layout.simple_spinner_item, paisesList);
                    adapterPaises.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerPaises.setAdapter(adapterPaises);

                    spinnerPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            int selectedPaisId = position + 1; // Esto es un ejemplo, debes obtener el ID del país desde tu lista o desde el servidor
                            btnContinuar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Activity_Destination_Record.this, City_Activity.class);
                                    intent.putExtra("selected_country_id", selectedPaisId);
                                    startActivity(intent);
                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Activity_Destination_Record.this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_Destination_Record.this, "Error al obtener datos de países: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueuePaises = Volley.newRequestQueue(this);
        requestQueuePaises.add(stringRequestPaises);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes obtener el país seleccionado y realizar la acción necesaria
            }
        });

        // Resto del código para los botones imageBack y imageHome

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