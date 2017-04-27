package com.uninorte.transdigital;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class Propietario extends Fragment {
    public Spinner categoria,clasev,clases,mdt,radioa;
    public int dia,mes,ano;
    public String cat="",cs="",mt="",rada="";
    String cv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_propietario, container, false);
        clasev = (Spinner) rootView.findViewById(R.id.idClaseVehi);
        List<String> values2 = new ArrayList<String>();
        values2.add("Seleccione...");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clasev.setAdapter(dataAdapter2);
        clasev.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cv = parent.getItemAtPosition(position).toString();
                if (parent.getItemAtPosition(position).toString().equals("Seleccione...")) {
                    cv = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cv="";
            }
        });
        return rootView;
        /*Spinner sector=(Spinner)rootView .findViewById(R.id.idClaseVehi);
        List<String> values2 = new ArrayList<String>();
        values2.add("Seleccione...");
        values2.add("Residencial");
        values2.add("Industrial");
        values2.add("Comercial");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sector.setAdapter(dataAdapter2);
        sector.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cv=parent.getItemAtPosition(position).toString();
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    cv="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cv="";
            }
        });*/
    }
}
