package com.example.travelwise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{
    //5-Creamos variables de tipo arrayList
    ArrayList<Lugares> listaLugares;

    //6-Generamos el constructor que inicializa nuestras variables

    public Adaptador(ArrayList<Lugares> listaPersonajes) {
        this.listaLugares = listaPersonajes;
    }

    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //7- aqui pasamos el layout o tarjeta que realizamos
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_lugar_popular,null,false);
        return new Adaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        //11-Encargado de actualizar los datos de un ViewHolder ya existente
        holder.nombre.setText(listaLugares.get(position).getNombre());
        holder.descripcion.setText(listaLugares.get(position).getDescripcion());
        holder.imagen.setImageResource(listaLugares.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        //8-Indica el numero de elementos de la coleccion de datos
        return listaLugares.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //9-Esta clase lo que hace es que enlazamos los componentes que diseñamos en nuestro layout
        TextView nombre,descripcion;
        ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //10- Dentro del constructor inicializamos los componentes
            nombre= itemView.findViewById(R.id.txtNombre);
            descripcion= itemView.findViewById(R.id.txtDescripcion);
            imagen= itemView.findViewById(R.id.imvImagen);
        }
    }
}
