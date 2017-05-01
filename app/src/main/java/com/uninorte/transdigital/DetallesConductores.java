package com.uninorte.transdigital;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Delete;

import java.util.ArrayList;
import java.util.List;


public class DetallesConductores extends Fragment {
    ImageButton imageButtonSave;
    EditText categoria,egez,nlic,fecha,cof,hosp,extra;
    RadioGroup rg,examen,aut,ebriagez,gradoE,sustancias,portalicencia,estadol,catego,co_of,chal,casc,cintu;
    public Spinner clasev,clases,mdt,radioa;
    public int dia,mes,ano;
    public String cat="",g,e,a,embr,gE,sust,portlia,idlicencia,fcha,el,cod_of,chaleco,casco,cinturon,hospital,descip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detalles_cond, container, false);
        categoria=(EditText)rootView.findViewById(R.id.idCatCond);
        rg=(RadioGroup) rootView.findViewById(R.id.grav);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.muerto) {
                    g = "Muerto";
                } else if (checkedId == R.id.herido) {
                    g = "Herido";
                }
            }

        });
        examen=(RadioGroup) rootView.findViewById(R.id.idexamencond);
        examen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    e = "Si";
                } else if (checkedId == R.id.No) {
                    e = "No";
                }
            }
        });
        aut=(RadioGroup) rootView.findViewById(R.id.idAutori);
        aut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    a = "Si";
                } else if (checkedId == R.id.No) {
                    a = "No";
                }
            }
        });
        ebriagez=(RadioGroup) rootView.findViewById(R.id.embiaguezcond);
        ebriagez.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.mas) {
                    embr = "+";
                } else if (checkedId == R.id.menos) {
                    embr = "-";
                }
            }
        });
        egez=(EditText) rootView.findViewById(R.id.gradoembri);
        sustancias=(RadioGroup) rootView.findViewById(R.id.sustanpsicoac);
        sustancias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    sust = "Si";
                } else if (checkedId == R.id.No) {
                    sust = "No";
                }
            }
        });
        portalicencia=(RadioGroup) rootView.findViewById(R.id.portalicencond);
        portalicencia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    portlia= "Si";
                } else if (checkedId == R.id.No) {
                    portlia = "No";
                }
            }
        });
        nlic=(EditText) rootView.findViewById(R.id.idLicenConductor);
        estadol=(RadioGroup) rootView.findViewById(R.id.estadoLicenCond);
        estadol.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.exp) {
                    el= "Exp";
                } else if (checkedId == R.id.ven) {
                    el = "Vencida";
                }
            }
        });
        fecha=(EditText) rootView.findViewById(R.id.efechalicen);
        cof=(EditText)rootView.findViewById(R.id.codOfiTraCon);
        chal=(RadioGroup) rootView.findViewById(R.id.idChaleco);
        chal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    chaleco= "Si";
                } else if (checkedId == R.id.No) {
                    chaleco = "No";
                }
            }
        });
        casc=(RadioGroup) rootView.findViewById(R.id.idCasco);
        casc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    casco= "Si";
                } else if (checkedId == R.id.No) {
                    casco = "No";
                }
            }
        });
        cintu=(RadioGroup) rootView.findViewById(R.id.idCinturon);
        cintu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    cinturon= "Si";
                } else if (checkedId == R.id.No) {
                    cinturon = "No";
                }
            }
        });
        hosp=(EditText)rootView.findViewById(R.id.atencionCond);
        extra=(EditText)rootView.findViewById(R.id.DescripcLesionCond);

        imageButtonSave = (ImageButton) rootView.findViewById(R.id.SaveDetaCond);
        imageButtonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cat=categoria.getText().toString();
                gE=egez.getText().toString();
                idlicencia=nlic.getText().toString();
                fcha=fecha.getText().toString();
                cod_of=cof.getText().toString();
                hospital=hosp.getText().toString();
                descip=extra.getText().toString();
                //cat,g,e,a,embr,gE,sust,portlia,idlicencia,fcha,el,cod_of,chaleco,casco,cinturon,hospital,descip;
                List<DetallesCond> b = new Delete().from(DetallesCond.class).queryList();
                DetallesCond dc=new DetallesCond();
                dc.setGravedad(g);
                dc.setExamen(e);
                dc.setCategoria(cat);
                dc.setAut(a);
                dc.setEbriagez(embr);
                dc.setPortalicencia(portlia);
                dc.setIdlicencia(idlicencia);
                dc.setFecha(fcha);
                dc.setEstado(el);
                dc.setCod_of(cod_of);
                dc.setChaleco(chaleco);
                dc.setCasco(casco);
                dc.setCinturon(cinturon);
                dc.setHospital(hospital);
                dc.setDescip(descip);
                dc.save();
                Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
