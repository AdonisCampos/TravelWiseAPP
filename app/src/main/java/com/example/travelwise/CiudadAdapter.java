package com.example.travelwise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CiudadAdapter extends ArrayAdapter<String> {
    private List<String> ciudadesList;
    private Context context;

    public CiudadAdapter(Context context, List<String> ciudadesList) {
        super(context, R.layout.list_item_ciudad, ciudadesList);
        this.ciudadesList = ciudadesList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_ciudad, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.txtCiudad = convertView.findViewById(R.id.textCiudad);
            viewHolder.btnEliminar = convertView.findViewById(R.id.btnEliminar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String ciudad = ciudadesList.get(position);

        viewHolder.txtCiudad.setText(ciudad);

        viewHolder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciudadesList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Se eliminó la ciudad: " + ciudad, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txtCiudad;
        Button btnEliminar;
    }
}
