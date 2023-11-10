package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Check_In_Activity extends AppCompatActivity {
    Button iniciarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        iniciarSesion = findViewById(R.id.btnRegistrado);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Check_In_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}