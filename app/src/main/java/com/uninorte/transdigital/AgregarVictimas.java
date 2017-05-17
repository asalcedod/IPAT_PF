package com.uninorte.transdigital;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.raizlabs.android.dbflow.sql.language.Delete;

import java.util.ArrayList;
import java.util.List;

public class AgregarVictimas extends AppCompatActivity {
    EditText editText1,devic,nom,tdc,ndc,nac,fechn,se,dir,ciud,telef,grdoE,hospi;;
    String detalle_victima="",nombre="",tdoc="",ndoc="",nacionalidad="",fecha_n="",x="",direc="",ciudad="",tel="",gravedad="",exam="",aut="",ebriagez="",gradoE="",sustancia="",chaleco="",casco="",cinturon="",hospital="";
    public String TAG = Constants.TAG;
    private boolean customview;
    public ImageButton bfechnavic;
    public EditText efechnavict;
    Spinner tipo_victima;
    RadioGroup rg,sexo,examen,auto,ebriag,sustancias,chal,casc,cintu;
    public int dia,mes,ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_victimas);
        nom = (EditText) findViewById(R.id.name);
        tdc = (EditText) findViewById(R.id.Tced);
        ndc = (EditText) findViewById(R.id.idvict);
        nac = (EditText) findViewById(R.id.nacionalidadvict);
        fechn = (EditText) findViewById(R.id.efechnavict);
        dir = (EditText) findViewById(R.id.direcvict);
        ciud = (EditText) findViewById(R.id.ciudvict);
        telef = (EditText) findViewById(R.id.telvict);
        tipo_victima = (Spinner) findViewById(R.id.TipoVictima);
        List<String> detalles_v = new ArrayList<String>();
        detalles_v.add("Seleccione...");
        detalles_v.add("Peaton");
        detalles_v.add("Pasajero");
        detalles_v.add("Acompañante");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, detalles_v);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo_victima.setAdapter(dataAdapter);
        tipo_victima.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                detalle_victima = parent.getItemAtPosition(position).toString();
                if (parent.getItemAtPosition(position).toString().equals("Seleccione...")) {
                    detalle_victima = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                detalle_victima="";
            }
        });
        sexo=(RadioGroup) findViewById(R.id.sexovict);
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
        rg=(RadioGroup) findViewById(R.id.grav);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.muerto) {
                    gravedad = "Muerto";
                } else if (checkedId == R.id.herido) {
                    gravedad = "Herido";
                }
            }

        });
        examen=(RadioGroup) findViewById(R.id.idexamenvict);
        examen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    exam = "Si";
                } else if (checkedId == R.id.No) {
                    exam = "No";
                }
            }
        });
        auto=(RadioGroup) findViewById(R.id.idAutoriVict);
        auto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    aut = "Si";
                } else if (checkedId == R.id.No) {
                    aut = "No";
                }
            }
        });
        ebriag=(RadioGroup) findViewById(R.id.embiaguezvict);
        ebriag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.mas) {
                    ebriagez = "+";
                } else if (checkedId == R.id.menos) {
                    ebriagez = "-";
                }
            }
        });
        grdoE=(EditText) findViewById(R.id.gradoembri);
        sustancias=(RadioGroup) findViewById(R.id.sustanpsicoac);
        sustancias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.Si) {
                    sustancia = "Si";
                } else if (checkedId == R.id.No) {
                    sustancia = "No";
                }
            }
        });
        chal=(RadioGroup) findViewById(R.id.idChaleco);
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
        casc=(RadioGroup) findViewById(R.id.idCasco);
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
        cintu=(RadioGroup) findViewById(R.id.idCinturon);
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
        editText1 = (EditText) findViewById(R.id.name);

        Bundle bundle= getIntent().getExtras();


        if (bundle!=null){
            customview=true;
            DataEntry dataEntry= (DataEntry) getIntent().getSerializableExtra("Entryview");
            editText1.setText(dataEntry.field1+"");
        }else{
            customview=false;
        }

        editText1.setFocusable(!customview);
        editText1.setFocusableInTouchMode(!customview);

        //DATOS Victima
        bfechnavic = (ImageButton) findViewById(R.id.bfechnavict);
        efechnavict = (EditText) findViewById(R.id.efechnavict);


    }
    public void botonfechnacond(View view) {
        fecha();
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void fecha(){
        final Calendar c;
        c = Calendar.getInstance();
        c.set(Calendar.YEAR,2000);
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                set(year, month, dayOfMonth);
            }
        }
                //Tenias esto al reves (,dia,mes,ano
                ,ano,mes,dia);
        datePickerDialog.show();

    }


    public void set(int year, int month, int dayOfMonth){
        EditText et_setDate;
        String fecha=dayOfMonth+"/"+(month+1)+"/"+year;
                et_setDate = (EditText) findViewById(R.id.efechnavict);
                et_setDate.setText(fecha);
        }


    //termine hasta el video 3 - creacion de la primera entrada---------------------------
    public void onClickAceptar(View view) {
        if(customview){
            finish();
        }
        if (TextUtils.isEmpty(editText1.getText().toString())){
            editText1.setError("No puede estar vacio");
        }   else {
            //sustancias;
            DBVictima dv = new DBVictima();
            dv.setDetalle_victima(detalle_victima);
            dv.setNombre(nom.getText().toString());
            dv.setTdoc(tdc.getText().toString());
            dv.setNdoc(ndc.getText().toString());
            dv.setNacionalidad(nac.getText().toString());
            dv.setFecha_n(fechn.getText().toString());
            dv.setSexo(x);
            dv.setDirec(dir.getText().toString());
            dv.setCiudad(ciud.getText().toString());
            dv.setTel(telef.getText().toString());
            dv.setGradoE(gradoE);
            dv.setSustancias(sustancia);
            dv.setGravedad(gravedad);
            dv.setExamen(exam);
            dv.setAut(aut);
            dv.setEbriagez(ebriagez);
            dv.setChaleco(chaleco);
            dv.setCasco(casco);
            dv.setCinturon(cinturon);
            dv.setHospital(hospital);
            dv.save();

            DataEntry de = new DataEntry(editText1.getText().toString());
            Intent i = getIntent();
            i.putExtra("entry", de);
            setResult(Activity.RESULT_OK, i);
            finish();
            }
        }

}
