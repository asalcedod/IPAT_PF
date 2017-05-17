package com.uninorte.transdigital;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;


public class DatosPersonales extends Fragment {
    ImageButton imageButtonSave;
    RadioGroup sexo;
    String x="";
    EditText nombre,tdoc,ndoc,nacionalidad,fecha_n,direc,ciudad,tel;
    FloatingActionButton save;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_datos_personales, container, false);
        imageButtonSave = (FloatingActionButton) rootview.findViewById(R.id.Save);
        nombre = (EditText) rootview.findViewById(R.id.name);
        tdoc = (EditText) rootview.findViewById(R.id.Tced);
        ndoc = (EditText) rootview.findViewById(R.id.idcond);
        nacionalidad = (EditText) rootview.findViewById(R.id.nacionalidadcond);
        fecha_n = (EditText) rootview.findViewById(R.id.efechnacond);
        sexo = (RadioGroup) rootview.findViewById(R.id.sexocond);
        direc = (EditText) rootview.findViewById(R.id.direcond);
        ciudad = (EditText) rootview.findViewById(R.id.ciudcond);
        tel = (EditText) rootview.findViewById(R.id.telcond);
        sexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.sexoF) {
                    x = "F";
                } else if (checkedId == R.id.sexoM) {
                    x = "M";
                }
            }

        });
        imageButtonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                List<DBDatosP> a = new Delete().from(DBDatosP.class).queryList();
                DBDatosP dp = new DBDatosP();
                String nombreC = nombre.getText().toString();
                String TdocC = tdoc.getText().toString();
                String IdentC = ndoc.getText().toString();

                dp.setNombre(nombreC);
                dp.setTdoc(TdocC);
                dp.setNdoc(IdentC);
                dp.setNacionalidad(nacionalidad.getText().toString());
                dp.setFecha_n(fecha_n.getText().toString());
                dp.setSexo(x);
                dp.setDirec(direc.getText().toString());
                dp.setCiudad(ciudad.getText().toString());
                dp.setTel(tel.getText().toString());
                dp.save();
                List<DBDatosP> c = new Select().from(DBDatosP.class).queryList();


                //pasar datos a fragment
                DatosPersonales fragment = new DatosPersonales();

                Bundle args = new Bundle();
                args.putString("nombreC", nombreC);
                args.putString("TdocC", TdocC);
                args.putString("IdenC", IdentC);
                Log.d("valor", nombreC);
                fragment.setArguments(args);
                Log.d("Args",String.valueOf(args));//Bundle[{IdenC=, TdocC=, nombreC=Lauryv }]


                //

                for (DBDatosP ca : c) {
                    Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return rootview;
    }




}
