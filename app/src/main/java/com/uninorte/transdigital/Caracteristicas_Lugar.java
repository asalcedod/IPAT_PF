package com.uninorte.transdigital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

public class Caracteristicas_Lugar extends AppCompatActivity {
    Spinner area,sector,zona,diseño,condicionc;
    String ar,sec,zo,dis,condc;
    int[] in=new int[5];

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("position",in);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
                ar=position+"";
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    ar="";
                }else{
                    in[0]=position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ar="";
            }
        });
        List<DBCaracteristicasl> c = new Select().from(DBCaracteristicasl.class).queryList();
        if(c.size()>0){
            for (DBCaracteristicasl ca : c) {
                for(int i=0;i<values1.size();i++) {
                    if(ca.area.equals(i+"")){
                        area.setSelection(i);
                    }
                }
            }
        }
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
                sec=position+"";
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    sec="";
                }else{
                    in[1]=position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sec="";
            }
        });
        if(c.size()>0){
            for (DBCaracteristicasl ca : c) {
                for(int i=0;i<values2.size();i++) {
                    if(ca.sector.equals(i+"")){
                        sector.setSelection(i);
                    }
                }
            }
        }
        zona=(Spinner)findViewById(R.id.zona);
        List<String> values3 = new ArrayList<String>();
        values3.add("Seleccione...");
        values3.add("Escolar");
        values3.add("Turística");
        values3.add("Deportiva");
        values3.add("Privada");
        values3.add("Hospitalaria");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zona.setAdapter(dataAdapter3);
        zona.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                zo=position+"";
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    zo="";
                }else{
                    in[2]=position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                zo="";
            }
        });
        if(c.size()>0){
            for (DBCaracteristicasl ca : c) {
                for(int i=0;i<values3.size();i++) {
                    if(ca.zona.equals(i+"")){
                        zona.setSelection(i);
                    }
                }
            }
        }
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
                dis=position+"";
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    dis="";
                }else{
                    in[3]=position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dis="";
            }
        });
        if(c.size()>0){
            for (DBCaracteristicasl ca : c) {
                for(int i=0;i<values4.size();i++) {
                    if(ca.diseño.equals(i+"")){
                        diseño.setSelection(i);
                    }
                }
            }
        }
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
                condc=position+"";
                if(parent.getItemAtPosition(position).toString().equals("Seleccione...")){
                    condc="";
                }else{
                    in[4]=position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                condc="";
            }
        });
        if(c.size()>0){
            for (DBCaracteristicasl ca : c) {
                for(int i=0;i<values5.size();i++) {
                    if(ca.condicionc.equals(i+"")){
                        condicionc.setSelection(i);
                    }
                }
            }
        }
    }

    public void Save_Caract_Lugar(View view) {
        List<DBCaracteristicasl> b = new Delete().from(DBCaracteristicasl.class).queryList();
        Intent i = new Intent();
        if(!ar.equals("") && !sec.equals("") && !zo.equals("") && !dis.equals("") && !condc.equals("") ) {
            DBCaracteristicasl caracter = new DBCaracteristicasl();
            caracter.setArea(ar);
            caracter.setSector(sec);
            caracter.setZona(zo);
            caracter.setDiseño(dis);
            caracter.setCondicionc(condc);
            caracter.setId_camp1(getIntent().getStringExtra("idinfo"));
            caracter.save();
            i.putExtra("area", ar);
            i.putExtra("sector", sec);
            i.putExtra("zona", zo);
            i.putExtra("diseño", dis);
            i.putExtra("condicionc", condc);
            i.putExtra("idcl",getIntent().getStringExtra("idinfo"));
            finish();
        }else{
            Toast.makeText(this, "Hay campos vacíos, verifique e intentelo de nuevo", Toast.LENGTH_SHORT).show();
        }
    }
}