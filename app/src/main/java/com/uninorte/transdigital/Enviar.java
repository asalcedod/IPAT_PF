package com.uninorte.transdigital;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    String id_cl="";
    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //testing on Emulator:
    private static final String CAMPO1_URL = "https://transitodigital-asalcedod.c9users.io/accidente.php";
    private static final String CAR_LUG_URL = "https://transitodigital-asalcedod.c9users.io/car_lug.php";
    private static final String REGISTER_URL3 = "https://transitodigital-asalcedod.c9users.io/accidente.php";
    private static final String REGISTER_URL4 = "https://transitodigital-asalcedod.c9users.io/accidente.php";
    private static final String REGISTER_URL5 = "https://transitodigital-asalcedod.c9users.io/accidente.php";

    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);


    }

    public void onClick_Informe(View view) {
        String organismo = "",gravedad = "",direccion_a="",latitud = "",longitud="",clase_a="",choque_con="",objeto_fijo="",id_c_l="",fecha_a="",hora_a="",fecha_i="",hora_i="";
        List<Accidente> c = new Select().from(Accidente.class).queryList();
        for (Accidente ca : c) {
            organismo = ca.ot;
            gravedad = ca.gravedad;
            Toast.makeText(this, gravedad, Toast.LENGTH_SHORT).show();
            direccion_a = ca.ubicacion;
            latitud = ca.latitud;
            longitud = ca.longitud;
            clase_a = ca.accidente;
            choque_con = ca.choque;
            objeto_fijo = ca.objetof;
            id_c_l = ca.caracteristicasl;
            id_cl=id_c_l;
            fecha_a = ca.a_fecha;
            hora_a = ca.a_hora;
            fecha_i = ca.r_fecha;
            hora_i = ca.r_hora;
        }
        String id_carac_l= "", area= "", sector= "", zona= "", diseño= "", condicionesc= "";
        new Enviar.Addform1().execute(organismo,gravedad,direccion_a,latitud,longitud,clase_a,choque_con,objeto_fijo,id_c_l,fecha_a,hora_a,fecha_i,hora_i);
        List<Caracteristicasl> cl = new Select().from(Caracteristicasl.class).queryList();
        for (Caracteristicasl ca : cl) {
            id_carac_l = id_cl;
            area = ca.area;
            sector = ca.sector;
            zona = ca.zona;
            diseño = ca.diseño;
            condicionesc = ca.condicionc;
        }
        //id_carac_l, area, sector, zona, diseño, condicionesc
        new Enviar.Caract_lugar().execute(id_carac_l, area, sector, zona, diseño, condicionesc);
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
            String longitud = args[4];
            String clase_a = args[5];
            String choque_con = args[6];
            String objeto_fijo = args[7];
            String id_c_l = args[8];
            String fecha_a = args[9];
            String hora_a = args[10];
            String fecha_i = args[11];
            String hora_i = args[12];

            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("organismo", organismo));
                params.add(new BasicNameValuePair("gravedad", gravedad));
                params.add(new BasicNameValuePair("direccion_a", direccion_a));
                params.add(new BasicNameValuePair("latitud", latitud));
                params.add(new BasicNameValuePair("longitud", longitud));
                params.add(new BasicNameValuePair("clase_a", clase_a));
                params.add(new BasicNameValuePair("choque_con", choque_con));
                params.add(new BasicNameValuePair("objeto_fijo", objeto_fijo));
                params.add(new BasicNameValuePair("id_caracteristicas_l", id_c_l));
                params.add(new BasicNameValuePair("fecha_a", fecha_a));
                params.add(new BasicNameValuePair("hora_a", hora_a));
                params.add(new BasicNameValuePair("fecha_i", fecha_i));
                params.add(new BasicNameValuePair("hora_i", hora_i));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        CAMPO1_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    //finish();
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
            //pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }


    class Caract_lugar extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String id_carac_l = args[0];
            String area = args[1];
            String sector = args[2];
            String zona = args[3];
            String diseño = args[4];
            String condicionesc = args[5];


            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("anexo1", id_carac_l));
                params.add(new BasicNameValuePair("area", area));
                params.add(new BasicNameValuePair("sector", sector));
                params.add(new BasicNameValuePair("zona", zona));
                params.add(new BasicNameValuePair("diseno", diseño));
                params.add(new BasicNameValuePair("condicionesc", condicionesc));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        CAR_LUG_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
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
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
