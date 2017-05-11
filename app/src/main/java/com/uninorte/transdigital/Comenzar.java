package com.uninorte.transdigital;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;

import java.util.List;

public class Comenzar extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1 ;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION_EXTRA_COMMANDS = 2;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 3;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-------------------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Comenzar.this,Intro.class);
                startActivity(i);
            }
        });
        //----------------------------------------------------------------------


        FlowManager.init(new FlowConfig.Builder(this).build());
        AnexoDane ad=new AnexoDane();
        /*INSTITUTO DPTAL. DE TTE Y TTO DEL ATLANTICO
INST. DISTRITAL DE TTO. Y TTE. DE BARRANQUILLA
INSTITUTO DPTAL. DE TTE. Y TTO. DE GALAPA
INSTITUTO DPTAL. DE TTE. Y TTO. DEL ATLANTICO
INSTIT. DEPTAL. DE TTE. Y TTO. DEL ATLANTICO
INSTITUTO MUNICIPAL DE TRANSITO Y TRANSPORTE DE SOLEDAD

*/
        ad.setId_dane(8000000);
        ad.setCiudad("Barranquilla");
        ad.setDepartamento("Atlántico");
        ad.setOficina_transito("INSTITUTO DPTAL. DE TTE Y TTO DEL ATLANTICO");
        ad.save();
        ad.setId_dane(8001000);
        ad.setCiudad("Barranquilla");
        ad.setDepartamento("Atlántico");
        ad.setOficina_transito("INST. DISTRITAL DE TTO. Y TTE. DE BARRANQUILLA");
        ad.save();
        ad.setId_dane(8296000);
        ad.setCiudad("Galapa");
        ad.setDepartamento("Atlántico");
        ad.setOficina_transito("INSTITUTO DPTAL. DE TTE. Y TTO. DE GALAPA");
        ad.save();
        ad.setId_dane(8433000);
        ad.setCiudad("Malambo");
        ad.setDepartamento("Atlántico");
        ad.setOficina_transito("INSTITUTO DPTAL. DE TTE. Y TTO. DEL ATLANTICO");
        ad.save();
        ad.setId_dane(8573000);
        ad.setCiudad("Puerto Colombia");
        ad.setDepartamento("Atlántico");
        ad.setOficina_transito("INSTIT. DEPTAL. DE TTE. Y TTO. DEL ATLANTICO");
        ad.save();
        ad.setId_dane(8758000);
        ad.setCiudad("Soledad");
        ad.setDepartamento("Atlántico");
        ad.setOficina_transito("INSTITUTO MUNICIPAL DE TRANSITO Y TRANSPORTE DE SOLEDAD");
        ad.save();
        List<Accidente> a = new Delete().from(Accidente.class).queryList();
        List<Caracteristicasl> b = new Delete().from(Caracteristicasl.class).queryList();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS},
                        MY_PERMISSIONS_REQUEST_ACCESS_LOCATION_EXTRA_COMMANDS);

            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_ACCESS_LOCATION_EXTRA_COMMANDS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }
    public void onClick_Informe(View view) {

        Intent i = new Intent(this,Anexo1.class);
        startActivityForResult(i,0);
    }
}
