package com.uninorte.transdigital;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class Propietario extends Fragment {
    ImageButton imageButtonSave,fallas,impacto;
    EditText Editimpacto;

    public Spinner categoria,clasev,clases,mdt,radioa;
    public int dia,mes,ano;
    public String cat="",cs="",mt="",rada="";
    String cv;
    private String[] Fallas;
    private boolean[] seleccionFallas;
    String[] items = {"Frontal","Lateral","Posterior"};
    private String[] Impacto;
    private boolean[] seleccionImpacto;

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

        imageButtonSave = (ImageButton) rootView.findViewById(R.id.SaveProp);
        imageButtonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        //lista de fallas en:----------------------------------
        Fallas= getResources().getStringArray(R.array.Fallas);
        seleccionFallas = new boolean[Fallas.length];

        fallas = (ImageButton) rootView.findViewById(R.id.iBFallas);
        fallas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.seleccion_de_fallas);
                builder.setMultiChoiceItems(R.array.Fallas, seleccionFallas, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        seleccionFallas[which] = isChecked;
                    }
                });
                builder.setPositiveButton(android.R.string.ok,null);
                builder.setNegativeButton(android.R.string.cancel,null);
                builder.create().show();
            }
        });
        //---------------------------------------------

        //lista de lugar de impacto
        Editimpacto = (EditText) rootView.findViewById(R.id.Impacto);;
        impacto = (ImageButton) rootView.findViewById(R.id.iBImpacto);
        impacto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lugar de impacto:");
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Editimpacto.setText(items[which]);
                    }


                });
                builder.setPositiveButton(android.R.string.ok,null);
                builder.setNegativeButton(android.R.string.cancel,null);
                builder.create().show();
            }
        });
        //---------------------------------------------




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
