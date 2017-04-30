package com.uninorte.transdigital;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class DatosVehi extends Fragment {
    ImageButton imageButtonSave;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_datos_vehi, container, false);
        imageButtonSave = (ImageButton) rootview.findViewById(R.id.SaveDatosVeh);
        imageButtonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
        return rootview;


    }
}
