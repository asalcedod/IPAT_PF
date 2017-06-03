package com.uninorte.transdigital;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Cond_Vehi_Prop extends AppCompatActivity {

    public ImageButton bfechnacond,bfechalicen,bfechavencSoat,bfechavencSSC,bfechavencSSE;
    public EditText efechnacond,efechalicen,efechavencSoat,efechavencSSC,efechavencSSE;
    long ahora = System.currentTimeMillis();
    Date fecha = new Date(ahora);
    public ImageButton iBFallas;
    public FloatingActionButton floatingActionButtonSend;

    public Spinner categoria,clasev,clases,mdt,radioa;
    public int dia,mes,ano;
    public String cat="",cv="",cs="",mt="",rada="";
    public TextView tit;
    private static final String TAG = "LogsAndroid";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cond__vehi__prop);
        String title=getIntent().getStringExtra("hoja");
        tit=(TextView)findViewById(R.id.toolbar_title);
        tit.setText(title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FlowManager.init(new FlowConfig.Builder(this).build());
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        //DATOS PERSONALES DEL CONDUCTOR
        bfechnacond = (ImageButton) findViewById(R.id.bfechnacond);
        efechnacond = (EditText) findViewById(R.id.efechnacond);

        //DETALLES DEL CONDUCTOR
        bfechalicen = (ImageButton) findViewById(R.id.bfechalicen);
        efechalicen = (EditText) findViewById(R.id.efechalicen);
        /*categoria = (Spinner) findViewById(R.id.CategLicCond);
        List<String> values = new ArrayList<String>();
        values.add("Seleccione...");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(dataAdapter);
        categoria.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = parent.getItemAtPosition(position).toString();
                if (parent.getItemAtPosition(position).toString().equals("Seleccione...")) {
                    cat = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cat="";
            }
        });
        clasev = (Spinner) findViewById(R.id.idClaseVehi);
        List<String> values2 = new ArrayList<String>();
        values2.add("Seleccione...");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clasev.setAdapter(dataAdapter2);
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
        */
        //VEHIC DETALLES
        //fechas de vencimiento
        bfechavencSoat = (ImageButton) findViewById(R.id.bfechavencSoat);
        efechavencSoat = (EditText) findViewById(R.id.efechavencSoat);

        bfechavencSSC = (ImageButton) findViewById(R.id.bfechavencSSC);
        efechavencSSC= (EditText) findViewById(R.id.efechavencSSC);

        bfechavencSSE = (ImageButton) findViewById(R.id.bfechavencSSE);
        efechavencSSE= (EditText) findViewById(R.id.efechavencSSE);


    }

    public void botonfechnacond(View view) {
        fecha(1);
    }
    public void botonfechalicen(View view) {
        fecha(2);
    }
    public void botonfechavencSoat(View view) {
        fecha(3);
    }
    public void botonfechavencSSC(View view) {
        fecha(4);
    }
    public void botonfechavencSSE(View view) {
        fecha(5);
    }
    @TargetApi(Build.VERSION_CODES.N)
    public void fecha(final int i){
        final Calendar c;
        c = Calendar.getInstance();
        c.set(Calendar.YEAR,2000);
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                set(i,year, month, dayOfMonth);
            }
        }
        //Tenias esto al reves (,dia,mes,ano
                ,ano,mes,dia);
        datePickerDialog.show();
    }

    public void set(int i,int year, int month, int dayOfMonth){
        List<DBAccidente> c = new Select().from(DBAccidente.class).queryList();
        String fecha_i="";
        for (DBAccidente ca : c) {
            fecha_i = ca.r_fecha;
        }
        EditText et_setDate;
        String fecha=dayOfMonth+"/"+(month+1)+"/"+year;
        DateFormat fech=new SimpleDateFormat("d/M/yyyy");
        Log.d(TAG, fecha);
        switch (i) {
            case 1:
                if(Integer.parseInt(fecha_i.split("/")[2])-year<16) {
                    Toast.makeText(this, "Fecha invalida, menor de 16 años", Toast.LENGTH_SHORT).show();
                }else{
                    if(Integer.parseInt(fecha_i.split("/")[2])-year==16) {
                        if(Integer.parseInt(fecha_i.split("/")[1])<(month+1)) {
                            Toast.makeText(this, "Fecha invalida, menor de 16 años", Toast.LENGTH_SHORT).show();
                        }else{
                            if(Integer.parseInt(fecha_i.split("/")[1])==(month+1)) {
                                if(Integer.parseInt(fecha_i.split("/")[0])<dayOfMonth) {
                                    Toast.makeText(this, "Fecha invalida, menor de 16 años", Toast.LENGTH_SHORT).show();
                                }else{
                                    et_setDate = (EditText) findViewById(R.id.efechnacond);
                                    et_setDate.setText(fecha);
                                }
                            }else{
                                et_setDate = (EditText) findViewById(R.id.efechnacond);
                                et_setDate.setText(fecha);
                            }
                        }
                    }else{
                        et_setDate = (EditText) findViewById(R.id.efechnacond);
                        et_setDate.setText(fecha);
                    }
                }
                et_setDate = (EditText) findViewById(R.id.efechnacond);
                et_setDate.setText(fecha);
                break;
            case 2:
                /*if(Integer.parseInt(fecha_i.split("/")[2])-year<16) {
                    Toast.makeText(this, "Fecha invalida, menor de 16 años", Toast.LENGTH_SHORT).show();
                }else{
                    if(Integer.parseInt(fecha_i.split("/")[2])-year==16) {
                        if(Integer.parseInt(fecha_i.split("/")[1])<(month+1)) {
                            Toast.makeText(this, "Fecha invalida, menor de 16 años", Toast.LENGTH_SHORT).show();
                        }else{
                            if(Integer.parseInt(fecha_i.split("/")[1])==(month+1)) {
                                if(Integer.parseInt(fecha_i.split("/")[0])<dayOfMonth) {
                                    Toast.makeText(this, "Fecha invalida, menor de 16 años", Toast.LENGTH_SHORT).show();
                                }else{
                                    et_setDate = (EditText) findViewById(R.id.efechalicen);
                                    et_setDate.setText(fecha);
                                }
                            }else{
                                et_setDate = (EditText) findViewById(R.id.efechalicen);
                                et_setDate.setText(fecha);
                            }
                        }
                    }else{
                        et_setDate = (EditText) findViewById(R.id.efechalicen);
                        et_setDate.setText(fecha);
                    }
                }*/
                et_setDate = (EditText) findViewById(R.id.efechalicen);
                et_setDate.setText(fecha);
                break;
            case 3:
                et_setDate = (EditText) findViewById(R.id.efechavencSoat);
                et_setDate.setText(fecha);
                break;
            case 4:
                et_setDate = (EditText) findViewById(R.id.efechavencSSC);
                et_setDate.setText(fecha);
                break;
            case 5:
                et_setDate = (EditText) findViewById(R.id.efechavencSSE);
                et_setDate.setText(fecha);
                break;
            default:
                break;


        }
    }

    public void onClick_Informe3(View view) {
        boolean sw=true;
        List<DBAccidente> c = new Select().from(DBAccidente.class).queryList();
        List<DBDatosP> dp = new Select().from(DBDatosP.class).queryList();
        List<DBDetallesCond> dc = new Select().from(DBDetallesCond.class).queryList();
        List<DBDatosV> dv = new Select().from(DBDatosV.class).queryList();
        List<DBDetallesV> dev = new Select().from(DBDetallesV.class).queryList();
        List<DBPropietario> p = new Select().from(DBPropietario.class).queryList();
        if(c.size()<=0){
            sw=false;
            Toast.makeText(this, "Anexo 1 esta vacío", Toast.LENGTH_SHORT).show();
        }
        if(dp.size()<=0){
            sw=false;
            Toast.makeText(this, "Datos personales esta vacío", Toast.LENGTH_SHORT).show();
        }
        if(dc.size()<=0){
            sw=false;
            Toast.makeText(this, "Datos del conductor esta vacío", Toast.LENGTH_SHORT).show();
        }
        if(dv.size()<=0){
            sw=false;
            Toast.makeText(this, "Datos del Vehículo esta vacío", Toast.LENGTH_SHORT).show();
        }
        if(dev.size()<=0){
            sw=false;
            Toast.makeText(this, "Detalles Vehículo esta vacío", Toast.LENGTH_SHORT).show();
        }
        if(p.size()<=0){
            sw=false;
            Toast.makeText(this, "Propietario esta vacío", Toast.LENGTH_SHORT).show();
        }
        if(sw==true) {
            Intent i = new Intent(Cond_Vehi_Prop.this, Victimas.class);
            startActivityForResult(i,2);
            //finish();
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cond__vehi__prop, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            LinearLayout linearLayoutExamenes = (LinearLayout) rootView.findViewById(R.id.layout_examenes);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    DatosPersonales datosPersonales = new DatosPersonales();
                    return datosPersonales;
                case 1:
                    DetallesConductores detallesConductores = new DetallesConductores();
                    return detallesConductores;
                case 2:
                    DatosVehi datosVehi = new DatosVehi();
                    return datosVehi;
                case 3:
                    DetallesVehi detallesVehi = new DetallesVehi();
                    return detallesVehi;
                case 4:
                    Propietario propietario = new Propietario();
                    return propietario;

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Datos Personales";
                case 1:
                    return "Detalles Conductores";
                case 2:
                    return "Datos Vehiculo";
                case 3:
                    return "Detalles Vehiculo";
                case 4:
                    return "Propietarios";

            }
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            if (resultCode == RESULT_OK) {
                Intent i=new Intent();
                setResult(RESULT_OK,i);
                finish();
            }
        }
    }
}
