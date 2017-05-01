package com.uninorte.transdigital;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;


public class DetallesVehi extends Fragment {
    ImageButton imageButtonSave;
    RadioGroup rev_tecnica,portaSOAT,portas,portas2;
    String emp,nt,matri,inmovi,dis,t_reg,rev_tecn,n_acomp,SOAT,id_s,pol,f_v_soat,porta_seguro,id_seg,asign,f_vsre,porta_seguro2,f_vsce;
    EditText empresa,nit,matriculado,inmovilizado,dispocicion,t_registro,n_acompañantes,id_soat,poliza,fecha_v_soat,id_seguro,asignatura,fecha_vsre,fecha_vsce;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_detalles_vehi, container, false);

        imageButtonSave = (ImageButton) rootview.findViewById(R.id.SaveDetaVeh);
        imageButtonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
        return rootview;
    }
}
