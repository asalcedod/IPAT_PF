package com.uninorte.transdigital;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.preference.EditTextPreference;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cond_Vehi_Prop extends AppCompatActivity {

    public Button bfechnacond,bfechalicen,bfechavencSoat,bfechavencSSC,bfechavencSSE;
    public EditText efechnacond,efechalicen,efechavencSoat,efechavencSSC,efechavencSSE;
    public int dia,mes,ano;

    private static final String TAG = "LogsAndroid";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cond__vehi__prop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cond_Vehi_Prop.this,Campo1.class);
                startActivity(i);
            }
        });


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        //DATOS PERSONALES DEL CONDUCTOR
        bfechnacond = (Button) findViewById(R.id.bfechnacond);
        efechnacond = (EditText) findViewById(R.id.efechnacond);

        //DETALLES DEL CONDUCTOR
        bfechalicen = (Button) findViewById(R.id.bfechalicen);
        efechalicen = (EditText) findViewById(R.id.efechalicen);

        //VEHIC DETALLES
        //fechas de vencimiento
        bfechavencSoat = (Button) findViewById(R.id.bfechavencSoat);
        efechavencSoat = (EditText) findViewById(R.id.efechavencSoat);

        bfechavencSSC = (Button) findViewById(R.id.bfechavencSSC);
        efechavencSSC= (EditText) findViewById(R.id.efechavencSSC);

        bfechavencSSE = (Button) findViewById(R.id.bfechavencSSE);
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
        EditText et_setDate;
        String fecha=dayOfMonth+"/"+(month+1)+"/"+year;
        Log.d(TAG, fecha);
        switch (i) {
            case 1:
                et_setDate = (EditText) findViewById(R.id.efechnacond);
                et_setDate.setText(fecha);
                break;
            case 2:
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
}
