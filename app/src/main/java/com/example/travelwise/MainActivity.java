package com.example.travelwise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    String usuario,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoRegistrado = findViewById(R.id.lblNoRegistrado);
        IniciarSesion = findViewById(R.id.btnSesion);
        CrearCuenta = findViewById(R.id.lblIniciarSesion);
        editUser = findViewById(R.id.txtUsuario);
        editPassword = findViewById(R.id.txtContraseña);
        recuperarPreferencias();

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
                usuario = editUser.getText().toString();
                password = editPassword.getText().toString();
                if (!usuario.isEmpty() && !password.isEmpty()){
                    validarUsuario("http://192.168.0.13/travelwise/validar_usuario.php");
                }else {
                    Toast.makeText(MainActivity.this, "Rellene los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void validarUsuario(String URL) {
      StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
              Log.d("Response", "Response: " + response); // Imprime la respuesta del servidor en el logcat

              if (response != null && !response.isEmpty() && !response.trim().equals("{\"message\":\"No se encontraron resultados\"}")) {
                  guardarPreferencias();
                  Toast.makeText(MainActivity.this, "Sesion iniciada", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(getApplicationContext(), Welcome_TravelWise.class);
                  startActivity(intent);
                  finish();
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
              parametros.put("usuario", usuario);
              parametros.put("password", password);
              return parametros;

          }
      };
      RequestQueue requestQueue = Volley.newRequestQueue(this);
      requestQueue.add(stringRequest);
    }
    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("password",password);
        editor.putBoolean("sesion",true);
        editor.commit();
    }
    private void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        editUser.setText(preferences.getString("usuario","admin"));
        editPassword.setText(preferences.getString("password", "123"));
    }
}























