package com.uninorte.transdigital;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class DetallesConductores extends Fragment {
    ImageButton imageButtonSave;
    public Spinner categoria,clasev,clases,mdt,radioa;
    public int dia,mes,ano;
    public String cat="",cv="",cs="",mt="",rada="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detalles_cond, container, false);
        categoria = (Spinner) rootView.findViewById(R.id.CategLicCond);
        List<String> values = new ArrayList<String>();
        values.add("Seleccione...");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(dataAdapter);
        categoria.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = parent.getItemAtPosition(position).toString();
                if (parent.getItemAtPosition(position).toString().equals("Seleccione...")) {
                    cat = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cat="";
            }
        });


        imageButtonSave = (ImageButton) rootView.findViewById(R.id.SaveDetaCond);
        imageButtonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
        return rootView;
    }
}
