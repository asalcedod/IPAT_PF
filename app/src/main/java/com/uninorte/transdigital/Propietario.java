package com.uninorte.transdigital;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;


public class Propietario extends Fragment {
    ImageButton fallas,impacto;
    FloatingActionButton imageButtonSave;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButtonsend;

    EditText Editimpacto,Editfallas,enombre,et_doc,id_doc,descrip;
    String mismo_cond="",nombre="",t_doc,n_doc,sclasev,sclases,modalidad_t,sradioa,falla,descrip_daño,lugar_impacto;
    public Spinner clasev,clases,mdt,radioa;
    public int dia,mes,ano;
    public String cat="",cs="",mt="",rada="";
    String cv="";
    public String cad="";
    String[] items = {"Frontal","Lateral","Posterior"};
    RadioGroup rg;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String Bnombre, Tdoc,Doc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_propietario, container, false);



        rg=(RadioGroup)rootView.findViewById(R.id.m_c);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.checkBox11) {
                    mismo_cond = "Si";

                    List<DBDatosP> dp = new Select().from(DBDatosP.class).queryList();
                    for (DBDatosP ca : dp) {
                        Bnombre=ca.nombre;
                        Tdoc=ca.tdoc;
                        Doc=ca.ndoc;
                    }
                    enombre=(EditText) rootView.findViewById(R.id.nameProp);
                    enombre.setText(Bnombre);

                    et_doc=(EditText)rootView.findViewById(R.id.tipoDOc);
                    et_doc.setText(Tdoc);

                    id_doc=(EditText)rootView.findViewById(R.id.idecond);
                    id_doc.setText(Doc);///eroro en asignar - se va al sino y muestra null
                    //recibe el dato
                    /*if (getArguments() != null) {
                        LinearLayout ln = (LinearLayout) rootView.findViewById(R.id.GrupoDatosProp);
                        //ln.setEnable(false); //no me deja desactivarlo

                        Bnombre = getArguments().getString("nombreC");
                        Tdoc = getArguments().getString("TdocC");
                        Doc= getArguments().getString("IdenC");

                        enombre=(EditText) rootView.findViewById(R.id.nameProp);
                        enombre.setText(Bnombre);

                        et_doc=(EditText)rootView.findViewById(R.id.tipoDOc);
                        et_doc.setText(Tdoc);

                        id_doc=(EditText)rootView.findViewById(R.id.idecond);
                        id_doc.setText(Doc);///eroro en asignar - se va al sino y muestra null

                    }else{
                        Log.d("El dato del cond es: ", String.valueOf(getArguments()));
                    }*/
                } else if (checkedId == R.id.checkBox10) {
                    mismo_cond = "No";
                }
            }

        });
        clasev = (Spinner) rootView.findViewById(R.id.idClaseVehi);

        List<String> values = new ArrayList<String>();
        values.add("Seleccione...");
        values.add("Automóvil");
        values.add("Bus");
        values.add("Buseta");
        values.add("Camión");
        values.add("Camioneta");
        values.add("Campero");
        values.add("Micro bus");
        values.add("Tracto Camión");
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
        values2.add("Público");
        values2.add("Particular");
        values2.add("Diplomático");
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
        clases=(Spinner) rootView.findViewById(R.id.idClaseServ);
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
        mdt= (Spinner) rootView.findViewById(R.id.idModalidadTransp);
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
        radioa= (Spinner) rootView.findViewById(R.id.idRadioAccion);
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

        imageButtonSave = (FloatingActionButton) rootView.findViewById(R.id.SaveProp);
        imageButtonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                enombre=(EditText)rootView.findViewById(R.id.nameProp);
                et_doc=(EditText)rootView.findViewById(R.id.tipoDOc);
                id_doc=(EditText)rootView.findViewById(R.id.idecond);
                descrip=(EditText)rootView.findViewById(R.id.idDesDaños);
                List<DBPropietario> a = new Delete().from(DBPropietario.class).queryList();
                DBPropietario p = new DBPropietario();
                p.setMismo_cond(mismo_cond);
                p.setNombre(enombre.getText().toString());
                p.setT_doc(et_doc.getText().toString());
                p.setN_doc(id_doc.getText().toString());
                p.setClasev(cv);
                p.setClases(cs);
                p.setModalidad_t(mt);
                p.setRadioa(rada);
                p.setFallas(Editfallas.getText().toString());
                p.setDescrip_daño(descrip.getText().toString());
                p.setLugar_impacto(Editimpacto.getText().toString());
                p.save();
                Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT).show();
            }
        });


        //lista de fallas en:----------------------------------
        listItems = getResources().getStringArray(R.array.Fallas);
        checkedItems = new boolean[listItems.length];

        Editfallas = (EditText) rootView.findViewById(R.id.id_Fallas);;


        fallas = (ImageButton) rootView.findViewById(R.id.iBFallas);
        fallas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder  = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle(R.string.seleccion_de_fallas);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

                        if(isChecked){
                            mUserItems.add(position);
                        }else{
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        Editfallas.setText(item);
                    }
                });
                mBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            mUserItems.clear();
                            Editfallas.setText("");
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
        //---------------------------------------------

        //lista de lugar de impacto
        Editimpacto = (EditText) rootView.findViewById(R.id.idImpacto);
        impacto = (ImageButton) rootView.findViewById(R.id.iBImpacto);
        impacto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lugar de impacto:");
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         cad = items[which];
                    }
                });
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        Editimpacto.setText(cad);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
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
