package com.uninorte.transdigital;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Enviar extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    private static final String TAG = "LogsAndroid";
    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //testing on Emulator:
    private static final String REGISTER_URL = "https://transitodigital-asalcedod.c9users.io/form.php";
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);


    }

    public void onClick_Informe(View view) {
        List<Accidente> c = new Select().from(Accidente.class).queryList();
        String organismo = "",gravedad = "",direccion_a="",latitud = "",longitud="",clase_a="",choque_con="",objeto_fijo="",id_c_l="",fecha_a="",hora_a="",fecha_i="",hora_i="";
        for (Accidente ca : c) {
            organismo = ca.ot;
            gravedad = ca.gravedad;
            direccion_a = ca.ubicacion;
            latitud = ca.latitud;
            longitud = ca.longitud;
            clase_a = ca.accidente;
            choque_con = ca.choque;
            objeto_fijo = ca.objetof;
            id_c_l = ca.caracteristicasl;
            fecha_a = ca.a_fecha;
            hora_a = ca.a_hora;
            fecha_i = ca.r_fecha;
            hora_i = ca.r_hora;
        }
        new Enviar.Addform1().execute(organismo,gravedad,direccion_a,latitud);
    }

    class Addform1 extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String organismo = args[0];
            String gravedad = args[1];
            String direccion_a = args[2];
            String latitud = args[3];
            /*String longitud = args[4];
            String clase_a = args[5];
            String choque_con = args[6];
            String objeto_fijo = args[7];
            String id_c_l = args[8];
            String fecha_a = args[9];
            String hora_a = args[10];
            String fecha_i = args[11];
            String hora_i = args[12];*/

            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("name", organismo));
                params.add(new BasicNameValuePair("date", gravedad));
                params.add(new BasicNameValuePair("hour", direccion_a));
                params.add(new BasicNameValuePair("latitud", latitud));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        REGISTER_URL, "POST", params);

                // full json response
                Log.d("Registering attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    finish();
                    //startActivity(it);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
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
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }

    

}
