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

    public Spinner clasev,clases,mdt,radioa;
    public int dia,mes,ano;
    public String cat="",cs="",mt="",rada="";
    String cv;
    private String[] Fallas;
    private boolean[] seleccionFallas;
    String[] items = {"Frontal","Lateral","Posterior"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_propietario, container, false);
        clasev = (Spinner) rootView.findViewById(R.id.idClaseVehi);
        List<String> values = new ArrayList<String>();
        values.add("Seleccione...");
        values.add("Automovil");
        values.add("Bus");
        values.add("Buseta");
        values.add("Camion");
        values.add("Camioneta");
        values.add("Campero");
        values.add("Micro bus");
        values.add("Tracto Camion");
        values.add("Volqueta");
        values.add("Motocicleta");
        values.add("M. Agricola");
        values.add("M. Industrial");
        values.add("Bicicleta");
        values.add("Motocarro");
        values.add("Mototriciclo");
        values.add("Traccion Animal");
        values.add("Motociclo");
        values.add("Cuatrimoto");
        values.add("Remolque");
        values.add("Semiremolque");
        List<String> values2 = new ArrayList<String>();
        values2.add("Seleccione...");
        values2.add("Oficial");
        values2.add("Publico");
        values2.add("Particular");
        values2.add("Diplomatico");
        List<String> values3 = new ArrayList<String>();
        values3.add("Seleccione...");
        values3.add("Mixto");
        values3.add("Carga");
        values3.add("Pasajero");
        List<String> values4 = new ArrayList<String>();
        values4.add("Seleccione...");
        values4.add("Nacional");
        values4.add("Municipal");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clasev.setAdapter(dataAdapter);
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

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clases.setAdapter(dataAdapter2);
        clases.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cs = parent.getItemAtPosition(position).toString();
                if (parent.getItemAtPosition(position).toString().equals("Seleccione...")) {
                    cs = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cs="";
            }
        });

        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mdt.setAdapter(dataAdapter3);
        mdt.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mt = parent.getItemAtPosition(position).toString();
                if (parent.getItemAtPosition(position).toString().equals("Seleccione...")) {
                    mt = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mt="";
            }
        });

        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values4);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        radioa.setAdapter(dataAdapter4);
        radioa.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rada = parent.getItemAtPosition(position).toString();
                if (parent.getItemAtPosition(position).toString().equals("Seleccione...")) {
                    rada = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rada="";
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
                        //falta agregar al editText
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
