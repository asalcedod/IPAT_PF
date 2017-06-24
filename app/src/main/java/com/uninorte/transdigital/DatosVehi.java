package com.uninorte.transdigital;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;


public class DatosVehi extends Fragment {
    FloatingActionButton imageButtonSave;
    RadioGroup nacionalidad;
    String pla="",remo="",naci="",mar="",lin="",col="",mode="",carroc="",tonel="",n_per="",id_l="";
    EditText placa,remorque,marca,linea,color,modelo,carroceria,toneladas,n_personas,id_licencia;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_datos_vehi, container, false);
        placa= (EditText) rootview.findViewById(R.id.idPlaca);
        remorque=(EditText) rootview.findViewById(R.id.idPlacaRemolque);
        nacionalidad = (RadioGroup)rootview.findViewById(R.id.idNacionalidadCond) ;
        nacionalidad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.idNacionalCol) {
                    naci = "Colombiano";
                } else if (checkedId == R.id.idNacionalExtran) {
                    naci = "Extranjero";
                }
            }

        });
        marca=(EditText) rootview.findViewById(R.id.idMarcaVeh);
        linea=(EditText) rootview.findViewById(R.id.idLineaVeh);
        color=(EditText) rootview.findViewById(R.id.idColorVeh);
        modelo=(EditText) rootview.findViewById(R.id.idModeloVeh);
        carroceria=(EditText) rootview.findViewById(R.id.idCarroceriaVeh);
        toneladas=(EditText) rootview.findViewById(R.id.idTonVeh);
        n_personas=(EditText) rootview.findViewById(R.id.idNumPasVeh);
        id_licencia=(EditText) rootview.findViewById(R.id.idNumLicenciaVeh);

        //---------------------------------------------------------------------------
        imageButtonSave = (FloatingActionButton) rootview.findViewById(R.id.SaveDatosVeh);
        imageButtonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pla=placa.getText().toString();
                remo=remorque.getText().toString();
                mar=marca.getText().toString();
                lin=linea.getText().toString();
                col=color.getText().toString();
                mode=modelo.getText().toString();
                carroc=carroceria.getText().toString();
                tonel=toneladas.getText().toString();
                n_per=n_personas.getText().toString();
                id_l=id_licencia.getText().toString();
                //List<DBDatosV> a = new Delete().from(DBDatosV.class).queryList();
                DBDatosV dv = new DBDatosV();
                dv.setPlaca(pla);
                dv.setRemorque(remo);
                dv.setNacionalidad(naci);
                dv.setMarca(mar);
                dv.setLinea(lin);
                dv.setColor(col);
                dv.setModelo(mode);
                dv.setCarroceria(carroc);
                dv.setToneladas(tonel);
                dv.setN_personas(n_per);
                dv.setId_licencia(id_l);
                dv.save();
                List<DBDatosV> c = new Select().from(DBDatosV.class).queryList();
                for (DBDatosV ca : c) {
                    Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootview;


    }
}
