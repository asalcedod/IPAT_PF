package com.uninorte.transdigital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class Cond_Vehi_Prop extends AppCompatActivity {
    TabHost TbH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cond__vehi__prop);

        TbH = (TabHost) findViewById(R.id.tabHost); //llamamos al Tabhost
        TbH.setup(); //lo activamos


        TabHost.TabSpec tab1 = TbH.newTabSpec("tab1");  //aspectos de cada Tab (pestaña)
        TabHost.TabSpec tab2 = TbH.newTabSpec("tab2");
        TabHost.TabSpec tab3 = TbH.newTabSpec("tab3");
        TabHost.TabSpec tab4 = TbH.newTabSpec("tab4");




        tab1.setIndicator("Datos Personales");    //qué queremos que aparezca en las pestañas
        tab1.setContent(R.id.datoscond); //definimos el id de cada Tab (pestaña)

        tab2.setIndicator("Detalles Conductores");
        tab2.setContent(R.id.condetalles);

        tab1.setIndicator("Datos Vehiculo");
        tab1.setContent(R.id.datosvehic);

        tab3.setIndicator("Detalles Vehiculo");
        tab3.setContent(R.id.vehicdetalles);

        tab4.setIndicator("Propietarios");
        tab4.setContent(R.id.propiet);

        TbH.addTab(tab1); //añadimos los tabs ya programados
        TbH.addTab(tab2);
        TbH.addTab(tab3);
        TbH.addTab(tab4);





    }
}
