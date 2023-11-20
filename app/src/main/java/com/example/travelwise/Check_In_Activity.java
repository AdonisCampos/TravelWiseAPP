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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Check_In_Activity extends AppCompatActivity {

    EditText nUser, nPassword,nPasswordConfirm, nEmail ;

    TextView IniciarSesion, Registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        IniciarSesion = findViewById(R.id.lblIniciarSesion);
        Registro = findViewById(R.id.btnRegistro);

        nUser = findViewById(R.id.txtUsuarioRegistrar);
        nPassword = findViewById(R.id.txtContraseñaRegistro);
        nPasswordConfirm = findViewById(R.id.txtContraseñaRegistroConfirmar);
        nEmail = findViewById(R.id.txtCorreo);

        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Check_In_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = nUser.getText().toString().trim();
                String email = nEmail.getText().toString();
                String password = nPassword.getText().toString();
                String passwordConfirm = nPasswordConfirm.getText().toString();

                if (verifyValues(user,email,password,passwordConfirm)){

                    createUser(user,email,password, "http://192.168.1.2/travelwise/nuevo_usuario.php");
                }else {
                    Toast.makeText(Check_In_Activity.this, "Debes completar todos los campos, \n ademas de coincidir las contrasaeñas.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean verifyValues(String user, String email, String password, String passwordConfirm) {
        // Validar que los valores no sean nulos o vacíos
        if (user == null || user.isEmpty() ||
                email == null || email.isEmpty() || !isValidEmail(email) ||
                password == null || password.isEmpty() ||
                passwordConfirm == null || passwordConfirm.isEmpty() || !password.equals(passwordConfirm)) {
            return false;
        }
        return true;
    }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    private void createUser(String user, String email, String password, String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean exito = jsonResponse.getBoolean("exito");
                    String mensaje = jsonResponse.getString("mensaje");

                    if (exito) {
                        finish();
                        Toast.makeText(Check_In_Activity.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Check_In_Activity.this, mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Check_In_Activity.this, "Error al procesar la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Check_In_Activity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", nEmail.getText().toString());
                parametros.put("usuario", nUser.getText().toString());
                parametros.put("password", nPassword.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}