package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Activity_Popular_Places extends AppCompatActivity {
    ArrayList<Lugares> listaLugares;
    RecyclerView recyclerLugares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_places);
        ImageButton btnLugarP = findViewById(R.id.btnPrinP);

        btnLugarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Popular_Places.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
        //13-
        listaLugares= new ArrayList<>();
        recyclerLugares=findViewById(R.id.cvLugares);
        recyclerLugares.setLayoutManager(new LinearLayoutManager(this));

        llenarLugares();

        //15-
        Adaptador adapter = new Adaptador(listaLugares);
        recyclerLugares.setAdapter(adapter);
    }

    private void llenarLugares() {
        //14- llenamos nuestros valores
        listaLugares.add(new Lugares("Antigua Guatemala","La ciudad más bonita de Guatemala.",R.drawable.antiguaguatemala));
        listaLugares.add(new Lugares("Granada\n(Nicaragua)","Una ciudad preciosa que ver en América Central.",R.drawable.granadanica));
        listaLugares.add(new Lugares("Ciudad de Panamá\ny el Canal ","El Canal de Panamá se considera como una de las obras de ingeniería más grandes de la historia.",R.drawable.panamaciudas));
        listaLugares.add(new Lugares("Alegría(El Salvador)","Es un rincón mágico, que alberga uno de los mejores cafés.",R.drawable.alegriasalvador));
    }
    }
