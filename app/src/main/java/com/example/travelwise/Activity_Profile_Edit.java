package com.example.travelwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity_Profile_Edit extends AppCompatActivity {
    ImageButton imagePin;
    Button btnEdit;
    EditText txtPassword, txtEmail;
    String idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        txtPassword = findViewById(R.id.txtContra);
        txtEmail = findViewById(R.id.txtCorreoEdit);
        btnEdit = findViewById(R.id.btnUpdate);
        imagePin = findViewById(R.id.btnP);
        imagePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Profile_Edit.this, Welcome_TravelWise.class);
                startActivity(intent);
            }
        });
        obtenerDatosUsuario();
    
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoCorreo = txtEmail.getText().toString();
                String nuevaContraseña = txtPassword.getText().toString();

                actualizarDatosUsuario(idUsuario, nuevoCorreo, nuevaContraseña);
            }
        });    

    }

    private void actualizarDatosUsuario(final String idUsuario, final String correo, final String contraseña) {
        String URL_ActualizarUsuario = "http://192.168.1.2/travelwise/editar_usuarios.php";

        // Creamos una solicitud POST para enviar los datos al servidor
        StringRequest stringRequestActualizar = new StringRequest(Request.Method.POST, URL_ActualizarUsuario,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean exito = jsonResponse.getBoolean("exito");
                            String mensaje = jsonResponse.getString("mensaje");

                            if (exito) {
                                Toast.makeText(Activity_Profile_Edit.this, mensaje, Toast.LENGTH_SHORT).show();
                                // Puedes hacer algo después de una actualización exitosa, si es necesario
                            } else {
                                Toast.makeText(Activity_Profile_Edit.this, mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Activity_Profile_Edit.this, "Error al procesar la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_Profile_Edit.this, "Error en la solicitud al servidor: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_usuario", idUsuario);
                params.put("correo_usuario", correo);
                params.put("password_usuario", contraseña);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestActualizar);
    }

    private void obtenerDatosUsuario() {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        String nombreUsuario = preferences.getString("usuario", "admin");

        // Modificar la URL para incluir el nombre de usuario como parámetro GET
        String URL_Usuarios = "http://192.168.1.2/travelwise/obtener_usuarios.php?nombre_usuario=" + nombreUsuario;

        StringRequest stringRequestUsuarios = new StringRequest(Request.Method.GET, URL_Usuarios,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            if (jsonArray.length() > 0) {
                                JSONObject jsonObject = jsonArray.getJSONObject(0);

                                String id = jsonObject.getString("id");
                                String usuario = jsonObject.getString("usuario");
                                String correo = jsonObject.getString("correo");

                                // Asignar valores a los EditText
                                txtEmail.setText(correo);
                                idUsuario = id;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Activity_Profile_Edit.this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_Profile_Edit.this, "Error al obtener datos de usuarios: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueueUsuarios = Volley.newRequestQueue(this);
        requestQueueUsuarios.add(stringRequestUsuarios);
    }

}