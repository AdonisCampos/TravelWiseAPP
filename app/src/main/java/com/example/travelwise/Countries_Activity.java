package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
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

public class Countries_Activity extends AppCompatActivity {
    ListView paises;
    List<String> paisesNombreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        setContentView(R.layout.activity_countries);
        String nombreUsuario = obtenerNombreUsuario();
        paises = findViewById(R.id.paisesListView);

        obtenerDatosDestinos(nombreUsuario);

        ImageButton imageButtonMore = findViewById(R.id.imagemore);
        ImageButton imageButtonback = findViewById(R.id.backHome);
        ImageButton imageButtonhome = findViewById(R.id.btnAnteCity);


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

    private String obtenerNombreUsuario() {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        return preferences.getString("usuario", ""); // Devuelve el valor almacenado con la clave "usuario"
    }

    private void obtenerDatosDestinos(String nombreUsuario) {
        String URLdestinos = "http://192.168.1.2/travelwise/destinos_listview.php";
        String URL = URLdestinos + "?nombre_usuario=" + nombreUsuario;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    final ArrayList<Integer> idDestinosList = new ArrayList<>(); // Lista para almacenar los ID de los destinos

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int idDestino = jsonObject.getInt("idDestino");
                        String tituloDestino = jsonObject.getString("titulo_destino");

                        paisesNombreList.add(tituloDestino);
                        idDestinosList.add(idDestino); // Almacena el idDestino correspondiente al título
                    }

                    ArrayAdapter<String> paisesAdapter = new ArrayAdapter<>(
                            Countries_Activity.this,
                            android.R.layout.simple_list_item_1,
                            paisesNombreList
                    );
                    paises.setAdapter(paisesAdapter);

                    paises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            int idDestinoSeleccionado = idDestinosList.get(position); // Obtiene el ID del destino seleccionado
                            // Envía el idDestino a la siguiente actividad
                            Intent intent = new Intent(Countries_Activity.this, Recommendations_add_and_cities_activity.class);
                            intent.putExtra("id_destino", idDestinoSeleccionado);
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Countries_Activity.this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Countries_Activity.this, "Error al obtener datos de destinos" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    // Método para convertir List<Integer> a int[]
    private int[] convertirIntegers(List<Integer> integers) {
        int[] ints = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            ints[i] = integers.get(i);
        }
        return ints;
    }
    }
