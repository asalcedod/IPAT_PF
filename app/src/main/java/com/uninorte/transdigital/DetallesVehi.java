package com.uninorte.transdigital;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;


public class DetallesVehi extends Fragment {
    ImageButton imageButtonSave;
    RadioGroup rev_tecnica,portaSOAT,portas,portas2;
    String emp="",nt="",matri="",inmovi="",dis,t_reg="",rev_tecn="",n_acomp="",SOAT="",id_s="",pol="",f_v_soat="",porta_seguro="",id_seg,asign="",f_vsre="",porta_seguro2="",f_vsce="";
    EditText id_rev,empresa,nit,matriculado,inmovilizado,dispocicion,t_registro,n_acompañantes,aseguradora,poliza,fecha_v_soat,id_seguro,asignatura,fecha_vsre,fecha_vsce;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_detalles_vehi, container, false);

        id_rev=(EditText)rootview.findViewById(R.id.editText);
        empresa=(EditText)rootview.findViewById(R.id.idEmpresa);
        nit=(EditText)rootview.findViewById(R.id.idNitVehic);
        matriculado=(EditText)rootview.findViewById(R.id.idMatricula);
        inmovilizado=(EditText)rootview.findViewById(R.id.idInmovilizado);
        dispocicion=(EditText)rootview.findViewById(R.id.idDisposInmov);
        t_registro=(EditText)rootview.findViewById(R.id.idTarjRVehic);
        rev_tecnica=(RadioGroup)rootview.findViewById(R.id.idRevisionTec);
        rev_tecnica.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.checkBox2) {
                    rev_tecn = "Si";
                } else if (checkedId == R.id.checkBox) {
                    rev_tecn = "No";
                }
            }

        });
        n_acompañantes=(EditText)rootview.findViewById(R.id.idNumerAcom);
        portaSOAT=(RadioGroup)rootview.findViewById(R.id.idSOAT);
        portaSOAT.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.checkBox3) {
                    SOAT = "Si";
                } else if (checkedId == R.id.checkBox4) {
                    SOAT = "No";
                }
            }

        });
        aseguradora=(EditText)rootview.findViewById(R.id.idAsegSOAT);
        poliza=(EditText)rootview.findViewById(R.id.idPoliza);
        fecha_v_soat=(EditText)rootview.findViewById(R.id.efechavencSoat);
        portas=(RadioGroup)rootview.findViewById(R.id.idSCContrac);
        portas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.checkBox5) {
                    porta_seguro = "Si";
                } else if (checkedId == R.id.checkBox6) {
                    porta_seguro = "No";
                }
            }

        });
        id_seguro=(EditText)rootview.findViewById(R.id.idSegContract);
        asignatura=(EditText)rootview.findViewById(R.id.idAsegContractual);
        portas2=(RadioGroup)rootview.findViewById(R.id.idSCExtracon);
        portas2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.checkBox7) {
                    porta_seguro2 = "Si";
                } else if (checkedId == R.id.checkBox8) {
                    porta_seguro2 = "No";
                }
            }

        });
        fecha_vsre=(EditText)rootview.findViewById(R.id.efechavencSSC);
        fecha_vsce=(EditText)rootview.findViewById(R.id.efechavencSSE);


        imageButtonSave = (ImageButton) rootview.findViewById(R.id.SaveDetaVeh);
        imageButtonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                emp=empresa.getText().toString();
                nt=nit.getText().toString();
                matri=matriculado.getText().toString();
                inmovi=inmovilizado.getText().toString();
                dis=dispocicion.getText().toString();
                t_reg=t_registro.getText().toString();
                n_acomp=n_acompañantes.getText().toString();
                id_s=aseguradora.getText().toString();
                pol=poliza.getText().toString();
                f_v_soat=fecha_v_soat.getText().toString();
                asign=asignatura.getText().toString();
                f_vsre=fecha_vsre.getText().toString();
                f_vsce=fecha_vsce.getText().toString();
                //List<DBDetallesV> a = new Delete().from(DBDetallesV.class).queryList();
                DBDetallesV dv = new DBDetallesV();
                dv.setEmpresa(emp);
                dv.setNit(nt);
                dv.setMatriculado(matri);
                dv.setInmovilizado(inmovi);
                dv.setDispocicion(dis);
                dv.setT_registro(t_reg);
                dv.setRev_tecnica(rev_tecn);
                dv.setNrevt(id_rev.getText().toString());
                dv.setN_acompañantes(n_acomp);
                dv.setSOAT(SOAT);
                dv.setAseguradora(id_s);
                dv.setPoliza(pol);
                dv.setFecha_v_soat(f_v_soat);
                dv.setPorta_seguro(porta_seguro);
                dv.setAsignatura(asign);
                dv.setFecha_vsre(f_vsre);
                dv.setPorta_seguro2(porta_seguro2);
                dv.setFecha_vsce(f_vsce);
                dv.save();
                List<DBDetallesV> c = new Select().from(DBDetallesV.class).queryList();
                for (DBDetallesV ca : c) {
                    Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootview;
    }
}
