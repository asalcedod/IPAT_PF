package com.uninorte.transdigital;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;

public class Cond_Vehi_Prop extends AppCompatActivity implements View.OnClickListener{
    TabHost TbH;
    Button bfechnacond,bfechalicen,bfechavencSoat,bfechavencSSC,bfechavencSSE;
    EditText efechnacond,efechalicen,efechavencSoat,efechavencSSC,efechavencSSE;
    private int dia,mes,ano,i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cond__vehi__prop);

        //tabs---------------------------------------------------------------------------------
        TbH = (TabHost) findViewById(R.id.tabHost); //llamamos al Tabhost
        TbH.setup(); //lo activamos


        TabHost.TabSpec tab1 = TbH.newTabSpec("tab1");  //aspectos de cada Tab (pestaña)
        TabHost.TabSpec tab2 = TbH.newTabSpec("tab2");
        TabHost.TabSpec tab3 = TbH.newTabSpec("tab3");
        TabHost.TabSpec tab4 = TbH.newTabSpec("tab4");
        TabHost.TabSpec tab5 = TbH.newTabSpec("tab5");

        tab1.setIndicator("Datos Personales");    //qué queremos que aparezca en las pestañas
        tab1.setContent(R.id.datoscond); //definimos el id de cada Tab (pestaña)

        tab2.setIndicator("Detalles Conductores");
        tab2.setContent(R.id.condetalles);

        tab3.setIndicator("Datos Vehiculo");
        tab3.setContent(R.id.datosvehic);

        tab4.setIndicator("Detalles Vehiculo");
        tab4.setContent(R.id.vehicdetalles);

        tab5.setIndicator("Propietarios");
        tab5.setContent(R.id.propiet);

        TbH.addTab(tab1); //añadimos los tabs ya programados
        TbH.addTab(tab2);
        TbH.addTab(tab3);
        TbH.addTab(tab4);
        TbH.addTab(tab5);
        //end tabs-----------------------------------------------------------------------------

        //DATOS PERSONALES DEL CONDUCTOR
        bfechnacond = (Button) findViewById(R.id.bfechnacond);
        efechnacond = (EditText) findViewById(R.id.efechnacond);
        bfechnacond.setOnClickListener(this);

        //DETALLES DEL CONDUCTOR
        bfechalicen = (Button) findViewById(R.id.bfechalicen);
        efechalicen = (EditText) findViewById(R.id.efechalicen);
        bfechalicen.setOnClickListener(this);

        //VEHIC DETALLES
        //fechas de vencimiento
        bfechavencSoat = (Button) findViewById(R.id.bfechavencSoat);
        efechavencSoat = (EditText) findViewById(R.id.efechavencSoat);
        bfechavencSoat.setOnClickListener(this);

        bfechavencSSC = (Button) findViewById(R.id.bfechavencSSC);
        efechavencSSC= (EditText) findViewById(R.id.efechavencSSC);
        bfechavencSSC.setOnClickListener(this);

        bfechavencSSE = (Button) findViewById(R.id.bfechavencSSE);
        efechavencSSE= (EditText) findViewById(R.id.efechavencSSE);
        bfechavencSSE.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if (v==bfechnacond){
            fecha(1);
        }
        if (v==bfechalicen){
            fecha(2);
        }
        if (v==bfechavencSoat){
            fecha(3);
        }
        if (v==bfechavencSSC){
            fecha(4);
        }
        if (v==bfechavencSSE){
            fecha(5);
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void fecha(final int i){
        final Calendar c;
        c = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                set (i,year, month, dayOfMonth);
            }
        }
                ,dia,mes,ano);
        datePickerDialog.show();

    }

    public void set (int i,int year, int month, int dayOfMonth){
        String fecha=dayOfMonth+"/"+(month+1)+"/"+year;
        switch (i) {
            case 1:
                efechnacond.setText(fecha);
                break;
            case 2:
                efechalicen.setText(fecha);
                break;
            case 3:
                efechavencSoat.setText(fecha);
                break;
            case 4:
                efechavencSSC.setText(fecha);
                break;
            case 5:
                efechavencSSE.setText(fecha);
                break;
            default:
                break;


        }
    }

    public void onClick_Informe3(View view) {
        //FALTA HACER-------------------------
        //victimas: pasajeros, acompañantes o peatones-- Recomendacion: usar un listview o recycler view, row, etc
    }
}

