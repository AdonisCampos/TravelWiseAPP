package com.example.travelwise;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CountriesView {
    ImageView itemImage;
    TextView CountriesTitle;
     TextView CountriesDescription;

     CountriesView(View v)
     {
         itemImage = v.findViewById(R.id.imagePaises);
         CountriesTitle = v.findViewById(R.id.txtTitulo);
         CountriesDescription = v.findViewById(R.id.txtDescripcionCiudad);
     }

}
