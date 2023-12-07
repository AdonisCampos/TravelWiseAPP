package com.example.travelwise;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class AdaptadorDestino extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> mDestinos;
    private List<Integer> mIdDestinos; // Lista de IDs de destino

    public AdaptadorDestino(Context context, List<String> destinos, List<Integer> idDestinos) {
        super(context, R.layout.list_destinatation_layout, destinos);
        this.mContext = context;
        this.mDestinos = destinos;
        this.mIdDestinos = idDestinos;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_destinatation_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewDestino = convertView.findViewById(R.id.textViewDestino);
            viewHolder.deleteButton = convertView.findViewById(R.id.buttonDelete);
            viewHolder.viewButton = convertView.findViewById(R.id.buttonView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String destino = mDestinos.get(position);
        viewHolder.textViewDestino.setText(destino);

        viewHolder.viewButton.setOnClickListener(v -> {
            int idDestino = mIdDestinos.get(position);
            Intent intent = new Intent(mContext, Recommendations_add_and_cities_activity.class);
            intent.putExtra("id_destino", idDestino);
            mContext.startActivity(intent);
        });

        viewHolder.deleteButton.setOnClickListener(v -> {
            int idDestino = mIdDestinos.get(position);
            eliminarDestino(idDestino);
            mDestinos.remove(position);
            mIdDestinos.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }

    private void eliminarDestino(int idDestino) {
        // Aquí realizas la lógica para eliminar el destino utilizando Volley
        // Crea tu solicitud Volley para eliminar el destino con el ID proporcionado
        // Puedes usar RequestQueue y StringRequest para enviar la solicitud HTTP al servidor
        // Asegúrate de manejar las respuestas y los errores apropiadamente
        // Aquí hay un ejemplo simplificado de cómo podrías hacerlo:

        String URLeliminarDestino = "http://192.168.0.13/travelwise/eliminar_destino.php";
        // Puedes enviar el ID a tu PHP para eliminar el destino
        String URL = URLeliminarDestino + "?id_destino=" + idDestino;

        StringRequest eliminarRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Manejar la respuesta del servidor (si es necesario)
                        // Por ejemplo, puedes mostrar un mensaje si la eliminación fue exitosa
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de la solicitud (si es necesario)
                        // Por ejemplo, mostrar un mensaje de error al usuario
                    }
                });

        // Agregar la solicitud a la cola de solicitudes (RequestQueue)
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(eliminarRequest);
    }

    static class ViewHolder {
        TextView textViewDestino;
        Button deleteButton;
        Button viewButton;
    }
}
