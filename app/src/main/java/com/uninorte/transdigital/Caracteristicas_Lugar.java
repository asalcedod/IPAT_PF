package com.uninorte.transdigital;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Caracteristicas_Lugar extends AppCompatActivity {
    Spinner area,sector,zona,diseño,condicionc;
    String ar,sec,zo,dis,condc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caracteristicas__lugar);

        //-------------------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //--------------------------------------------------------
        area=(Spinner)findViewById(R.id.area);
        List<String> values1 = new ArrayList<String>();
        values1.add("Seleccione...");
        values1.add("Rural");
        values1.add("Nacional");
        values1.add("Departamental");
        values1.add("Municipal");
        values1.add("Urbana");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area.setAdapter(dataAdapter);
        area.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ar=parent.getItemAtPosition(position).toString();
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    ar="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ar="";
            }
        });
        sector=(Spinner)findViewById(R.id.sector);
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
                sec=parent.getItemAtPosition(position).toString();
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    sec="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sec="";
            }
        });
        zona=(Spinner)findViewById(R.id.zona);
        List<String> values = new ArrayList<String>();
        values.add("Seleccione...");
        values.add("Escolar");
        values.add("Turística");
        values.add("Deportiva");
        values.add("Privada");
        values.add("Hospitalaria");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zona.setAdapter(dataAdapter3);
        zona.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                zo=parent.getItemAtPosition(position).toString();
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    zo="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                zo="";
            }
        });
        diseño=(Spinner)findViewById(R.id.diseño);
        List<String> values4 = new ArrayList<String>();
        values4.add("Seleccione...");
        values4.add("Glorieta");
        values4.add("Intersección");
        values4.add("Lote o predio");
        values4.add("Paso a nivel");
        values4.add("Pontón");
        values4.add("Ciclo ruta");
        values4.add("Paso elevado");
        values4.add("Peatonal");
        values4.add("Puente");
        values4.add("Tramo de vía");
        values4.add("Túnel");
        values4.add("Pontón");
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values4);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diseño.setAdapter(dataAdapter4);
        diseño.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dis=parent.getItemAtPosition(position).toString();
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    dis="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dis="";
            }
        });
        condicionc=(Spinner)findViewById(R.id.condicionc);
        List<String> values5 = new ArrayList<String>();
        values5.add("Seleccione...");
        values5.add("Granizo");
        values5.add("Lluvia");
        values5.add("Niebla");
        values5.add("Viento");
        values5.add("Normal");
        ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values5);
        dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        condicionc.setAdapter(dataAdapter5);
        condicionc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                condc=parent.getItemAtPosition(position).toString();
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    condc="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                condc="";
            }
        });


    }

    public void Save_Caract_Lugar(View view) {
        Intent i = new Intent();
        if(!ar.equals("") && !sec.equals("") && !zo.equals("") && !dis.equals("") && !condc.equals("") ) {
            Caracteristicasl caracter = new Caracteristicasl();
            caracter.setArea(ar);
            caracter.setSector(sec);
            caracter.setZona(zo);
            caracter.equals(dis);
            caracter.setCondicionc(condc);
            caracter.save();
            i.putExtra("area", ar);
            i.putExtra("sector", sec);
            i.putExtra("zona", zo);
            i.putExtra("diseño", dis);
            i.putExtra("condicionc", condc);
            setResult(Activity.RESULT_OK, i);
            finish();
        }else{
            Toast.makeText(this, "EXISTEN CAMPOS SIN SELECCIONAR", Toast.LENGTH_SHORT).show();
        }
    }
}
