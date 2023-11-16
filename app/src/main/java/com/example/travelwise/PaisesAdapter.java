package com.example.travelwise;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PaisesAdapter extends ArrayAdapter<String> {
    Context context;
    int [] images;
    String[] CountriesName;
    String[] CountriesDescription;
    public PaisesAdapter( Context context, String[]CountriesName, int []images, String[] CountriesDescription ) {
        super(context, R.layout.activity_countries, R.id.txtTitulo,CountriesName);
        this.context= context;
        this.images= images;
        this.CountriesName=CountriesName;
        this.CountriesDescription=CountriesDescription;


    }


    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        View singleItem = convertView;
        CountriesView holder = null;
        if (singleItem == null){
            LayoutInflater layautInflatter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layautInflatter.inflate(R.layout.single_item,parent ,false);
            holder = new CountriesView(singleItem);
            singleItem.setTag(holder);
        }else{
            holder = (CountriesView) singleItem.getTag();
        }
        holder.itemImage.setImageResource(images[position]);
        holder.CountriesTitle.setText(CountriesName[position]);
        holder.CountriesDescription.setText(CountriesDescription[position]);
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Recommendations_add_and_cities_activity.class);
                intent.putExtra("PAIS_SELECCIONADO", CountriesName[position]);
                context.startActivity(intent);
            }
        });

        return singleItem;
    }
}
