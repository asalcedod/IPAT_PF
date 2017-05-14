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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.raizlabs.android.dbflow.sql.language.Delete;

import java.util.List;

public class AgregarVictimas extends AppCompatActivity {
    EditText editText1;
    public String TAG = Constants.TAG;
    private boolean customview;
    public ImageButton bfechnavic;
    public EditText efechnavict;
    Spinner tipo_victima;
    public int dia,mes,ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_victimas);

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
            /*DBVictima dv = new DBVictima();
            dv.setNombre(nombre.getText().toString());
            dv.setTdoc(tdoc.getText().toString());
            dv.setNdoc(ndoc.getText().toString());
            dv.setNacionalidad(nacionalidad.getText().toString());
            dv.setFecha_n(fecha_n.getText().toString());
            dv.setSexo(x);
            dv.setDirec(direc.getText().toString());
            dv.setCiudad(ciudad.getText().toString());
            dv.setTel(tel.getText().toString());
            dv.setGravedad(g);
            dv.setExamen(e);
            dv.setAut(a);
            dv.setEbriagez(embr);
            dv.setChaleco(chaleco);
            dv.setCasco(casco);
            dv.setCinturon(cinturon);
            dv.setHospital(hospital);
            dv.save();*/
            DataEntry de = new DataEntry(editText1.getText().toString());
            Intent i = getIntent();
            i.putExtra("entry", de);
            setResult(Activity.RESULT_OK, i);
            finish();
            }
        }

}
