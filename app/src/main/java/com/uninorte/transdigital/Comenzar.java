package com.uninorte.transdigital;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cocoahero.android.geojson.Feature;
import com.cocoahero.android.geojson.Point;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import harmony.java.awt.Color;

public class Comenzar extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1 ;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION_EXTRA_COMMANDS = 2;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 3;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 4;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private ProgressDialog pDialog;
    Point point;
    // Clase JSONParser
    JSONParser jsonParser = new JSONParser();
    //ids---// La respuesta del JSON es
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_LATITUD = "latitud";
    private static final String TAG_LONGITUD = "longitud";
    private static final String DAT_URL = "https://transitodigital-asalcedod.c9users.io/datos.php";
    private final static String NOMBRE_DIRECTORIO = "MiPdf";
    private final static String GENERADOR = "MisArchivos";
    private final static String NOMBRE_DOCUMENTO = "prueba.pdf";
    private final static String ETIQUETA_ERROR = "ERROR";
    String lat,longt,text="";
    int succes=1;
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
        List<AnexoDane> inic = new Delete().from(AnexoDane.class).queryList();
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
        List<DBAccidente> a = new Delete().from(DBAccidente.class).queryList();
        List<DBCaracteristicasl> b = new Delete().from(DBCaracteristicasl.class).queryList();
        List<DBDatosP> c = new Delete().from(DBDatosP.class).queryList();
        List<DBDetallesCond> d = new Delete().from(DBDetallesCond.class).queryList();
        List<DBDatosV> e = new Delete().from(DBDatosV.class).queryList();
        List<DBDetallesV> f = new Delete().from(DBDetallesV.class).queryList();
        List<DBPropietario> g = new Delete().from(DBPropietario.class).queryList();
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
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        int permission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission2 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
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
        //GEOJSON
        /*int i=1;
        //while(succes==1) {
            new Comenzar.AttemptLogin().execute(i+"");
            Feature feature = new Feature(point);

            // Set optional feature identifier
            feature.setIdentifier("MyIdentifier");

            // Set optional feature properties
            feature.setProperties(new JSONObject());

            // Convert to formatted JSONObject
            JSONObject geoJSON = feature.toJSON();
        text=geoJSON.toString();
        /*    if(lat!=null && longt!=null) {
                text = text + ",'latitud':" + "'" + lat + "'" + ",";
                text = text + "'longitud':" + "'" + longt + "'";
            }
            i++;*/
        //}
        //text="{"+text.substring(1)+"}";*/
    }

        class AttemptLogin extends AsyncTask<String, String, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(Comenzar.this);
                pDialog.setMessage("Cargando...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }

            @Override
            protected String doInBackground(String... args) {
                int success;
                String id_formulario = args[0];

                try {
                    // Building Parameters
                    List params = new ArrayList();
                    params.add(new BasicNameValuePair("id_formulario", id_formulario));

                    Log.d("request!", "starting");
                    // getting product details by making HTTP request
                    JSONObject json = jsonParser.makeHttpRequest(DAT_URL, "POST",
                            params);
                    if (json != null) {
                        // check your log for json response
                        Log.d("Login attempt", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        succes = success;
                    } else {
                        return "Falla en el servidor";
                    }
                    if (success == 1) {
                        succes = success;
                        lat = json.getString(TAG_LATITUD);
                        longt = json.getString(TAG_LONGITUD);
                        point = new Point(Double.parseDouble(lat), Double.parseDouble(longt));
                        return json.getString(TAG_MESSAGE);
                    } else {
                        Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                        return json.getString(TAG_MESSAGE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;

            }

            protected void onPostExecute(String file_url) {
                // dismiss the dialog once product deleted
                pDialog.dismiss();
            }
        }

}
