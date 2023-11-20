package com.example.travelwise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView NoRegistrado;
    EditText editUser,editPassword;
    Button  IniciarSesion;
    TextView CrearCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoRegistrado = findViewById(R.id.lblNoRegistrado);
        IniciarSesion = findViewById(R.id.btnSesion);
        CrearCuenta = findViewById(R.id.lblIniciarSesion);
        editUser = findViewById(R.id.txtUsuario);
        editPassword = findViewById(R.id.txtContraseña);

        CrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Check_In_Activity.class);
                startActivity(intent);
            }
        });
        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario("http://192.168.1.2/travelwise/validar_usuario.php");
            }
        });


    }

    private void validarUsuario(String URL) {
      StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
              Log.d("Response", "Response: " + response); // Imprime la respuesta del servidor en el logcat

              if (response != null && !response.isEmpty() && !response.trim().equals("{\"message\":\"No se encontraron resultados\"}")) {
                  Toast.makeText(MainActivity.this, "Sesion iniciada", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(getApplicationContext(), Welcome_TravelWise.class);
                  startActivity(intent);
              } else {
                  Toast.makeText(MainActivity.this, "Parametros incorrectos", Toast.LENGTH_SHORT).show();
              }
          }

      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(MainActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();

          }
      }){
          @Nullable
          @Override
          protected Map<String, String> getParams() throws AuthFailureError {
              Map<String, String> parametros = new HashMap<String, String>();
              parametros.put("usuario", editUser.getText().toString());
              parametros.put("password", editPassword.getText().toString());
              return parametros;

          }
      };
      RequestQueue requestQueue = Volley.newRequestQueue(this);
      requestQueue.add(stringRequest);
    }
}