package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Add_recommendation extends AppCompatActivity {

    EditText DescripcionCiudad,TituloDestino;
    Button FinalizarDestino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nombreUsuario = obtenerNombreUsuario();

        setContentView(R.layout.activity_add_recommendation);
        ImageButton imageb3 = findViewById(R.id.btnAnteCity);
        ImageButton imageb4 = findViewById(R.id.btnPrinCity);

        DescripcionCiudad = findViewById(R.id.txtDescripcionCiudad);
        TituloDestino = findViewById(R.id.txtTituloDestino);

        FinalizarDestino = findViewById(R.id.btnFinalizarDestino);
        imageb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               finish();
            }
        });

        FinalizarDestino.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    // Obtener los datos ingresados por el usuario
                                                    String tituloDestino = TituloDestino.getText().toString().trim();
                                                    String descripcionCiudad = DescripcionCiudad.getText().toString().trim();

                                                    // Obtener el ID del país y las ciudades seleccionadas de la actividad anterior
                                                    Intent intent = getIntent();
                                                    int selectedCountryID = -1;
                                                    ArrayList<String> selectedCitiesList = new ArrayList<>();
                                                    if (intent != null && intent.hasExtra("selected_country_id") && intent.hasExtra("selected_cities_list")) {
                                                        selectedCountryID = intent.getIntExtra("selected_country_id", -1);
                                                        selectedCitiesList = intent.getStringArrayListExtra("selected_cities_list");
                                                    }

                                                    // Verificar si los campos están vacíos
                                                    if (TextUtils.isEmpty(tituloDestino) || TextUtils.isEmpty(descripcionCiudad) || selectedCountryID == -1 || selectedCitiesList.isEmpty()) {
                                                        Toast.makeText(Add_recommendation.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        // Enviar los datos al servidor usando Volley
                                                        enviarDatosAlServidor(selectedCountryID, selectedCitiesList, tituloDestino, descripcionCiudad, nombreUsuario);
                                                    }
                                                }
                                            });

        imageb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_recommendation.this,Welcome_TravelWise.class);
                startActivity(intent);
            }

        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selected_country_id") && intent.hasExtra("selected_cities_list")) {
            int selectedCountryID = intent.getIntExtra("selected_country_id", -1);
            ArrayList<String> selectedCitiesList = intent.getStringArrayListExtra("selected_cities_list");
        }

    }

    private String obtenerNombreUsuario() {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        return preferences.getString("usuario", ""); // Devuelve el valor almacenado con la clave "usuario"
    }

    private void enviarDatosAlServidor(int selectedCountryID, ArrayList<String> selectedCitiesList, String tituloDestino, String descripcionCiudad, String nombreUsuario) {
        String URL = "http://192.168.1.2/travelwise/insertar_destino.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Add_recommendation.this, "Datos enviados correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Add_recommendation.this, Countries_Activity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar errores de la solicitud
                Toast.makeText(Add_recommendation.this, "Error al enviar datos: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_pais", String.valueOf(selectedCountryID));
                params.put("titulo_destino", tituloDestino);
                params.put("recomendaciones", descripcionCiudad);
                params.put("ciudades_seleccionadas", TextUtils.join(",", selectedCitiesList));
                params.put("nombre_usuario", nombreUsuario);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}

