package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Countries_Activity extends AppCompatActivity {
    ListView paises;
    List<String> paisesNombreList = new ArrayList<>();
    List<Integer> idDestinosList = new ArrayList<>();
    AdaptadorDestino adaptadorDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        paises = findViewById(R.id.paisesListView);
        adaptadorDestino = new AdaptadorDestino(this, paisesNombreList, idDestinosList);
        paises.setAdapter(adaptadorDestino);

        obtenerDatosDestinos();
        setListeners();
    }

    private void setListeners() {
        ImageButton imageButtonMore = findViewById(R.id.imagemore);
        ImageButton imageButtonback = findViewById(R.id.backHome);
        ImageButton imageButtonhome = findViewById(R.id.btnAnteCity);

        imageButtonMore.setOnClickListener(v -> startActivity(new Intent(Countries_Activity.this, Activity_Destination_Record.class)));
        imageButtonback.setOnClickListener(v -> startActivity(new Intent(Countries_Activity.this, Welcome_TravelWise.class)));
        imageButtonhome.setOnClickListener(v -> startActivity(new Intent(Countries_Activity.this, Welcome_TravelWise.class)));

        paises.setOnItemClickListener((parent, view, position, id) -> {
            int idDestinoSeleccionado = idDestinosList.get(position);
            Intent intent = new Intent(Countries_Activity.this, Recommendations_add_and_cities_activity.class);
            intent.putExtra("id_destino", idDestinoSeleccionado);
            startActivity(intent);
        });
    }

    private void obtenerDatosDestinos() {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        String nombreUsuario = preferences.getString("usuario", "");

        String URL = "http://192.168.1.2/travelwise/destinos_listview.php?nombre_usuario=" + nombreUsuario;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            int idDestino = jsonObject.getInt("idDestino");
                            String tituloDestino = jsonObject.getString("titulo_destino");

                            paisesNombreList.add(tituloDestino);
                            idDestinosList.add(idDestino);
                        }

                        adaptadorDestino.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Countries_Activity.this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(Countries_Activity.this, "Error al obtener datos de destinos" + error.toString(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
