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
    private static final String DATOS_PERSONALES_URL = "https://transitodigital-asalcedod.c9users.io/datospersonales.php";
    private static final String DETALLES_CONDUCTOR_URL = "https://transitodigital-asalcedod.c9users.io/detallesconductor.php";
    private static final String DATOS_VEHICULO_URL = "https://transitodigital-asalcedod.c9users.io/datosvehiculo.php";
    private static final String DETALLES_VEHICULO_URL = "https://transitodigital-asalcedod.c9users.io/detallesvehiculo.php";
    private static final String PROPIETARIO_URL = "https://transitodigital-asalcedod.c9users.io/propietario.php";

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
        List<DBAccidente> c = new Select().from(DBAccidente.class).queryList();
        for (DBAccidente ca : c) {
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
        List<DBCaracteristicasl> cl = new Select().from(DBCaracteristicasl.class).queryList();
        for (DBCaracteristicasl ca : cl) {
            id_carac_l = id_cl;
            area = ca.area;
            sector = ca.sector;
            zona = ca.zona;
            diseño = ca.diseño;
            condicionesc = ca.condicionc;
        }
        new Enviar.Caract_lugar().execute(id_carac_l, area, sector, zona, diseño, condicionesc);
        String nombre="",tdoc="",ndoc="",nacionalidad="",fecha_n="",sexo="",direc="",ciudad="",tel="";
        List<DBDatosP> dp = new Select().from(DBDatosP.class).queryList();
        for (DBDatosP ca : dp) {
            nombre=ca.nombre;
            tdoc=ca.tdoc;
            ndoc=ca.ndoc;
            nacionalidad=ca.nacionalidad;
            fecha_n=ca.fecha_n;
            sexo=ca.sexo;
            direc=ca.direc;
            ciudad=ca.ciudad;
            tel=ca.tel;
        }
        //execute()
        String gravedad2="",examen="",aut="",ebriagez="",gradoE="",sustancias="",portalicencia="",idlicencia="",categoria="",estado="",fecha="",cod_of="",chaleco="",casco="",cinturon="",hospital="",descip="";
        List<DBDetallesCond> dec = new Select().from(DBDetallesCond.class).queryList();
        for (DBDetallesCond ca : dec) {
            //gravedad2,examen,aut,ebriagez,gradoE,sustancias,portalicencia,idlicencia,categoria,estado,fecha,cod_of,chaleco,casco,cinturon,hospital,descip
            gravedad2=ca.gravedad;
            examen=ca.examen;
            aut=ca.aut;
            ebriagez=ca.ebriagez;
            sustancias=ca.sustancias;
            portalicencia=ca.portalicencia;
            idlicencia=ca.idlicencia;
            categoria=ca.categoria;
            estado=ca.estado;
            fecha=ca.fecha;
            cod_of=ca.cod_of;
            chaleco=ca.chaleco;
            casco=ca.casco;
            cinturon=ca.cinturon;
            hospital=ca.hospital;
            descip=ca.descip;
        }
        //execute()
        String placa="",remorque="",nacionalidad2="",marca="",linea="",color="",modelo="",carroceria="",toneladas="",n_personas="",id_licencia="";
        List<DBDatosV> dv = new Select().from(DBDatosV.class).queryList();
        for (DBDatosV ca : dv) {
            //placa,remorque,nacionalidad,marca,linea,color,modelo,carroceria,toneladas,n_personas,id_licencia;
            placa=ca.placa;
            remorque=ca.remorque;
            nacionalidad2=ca.nacionalidad;
            marca=ca.marca;
            linea=ca.linea;
            color=ca.color;
            modelo=ca.modelo;
            carroceria=ca.carroceria;
            toneladas=ca.toneladas;
            n_personas=ca.n_personas;
            id_licencia=ca.id_licencia;
        }
        //execute()
        String empresa="",nit="",matriculado="",inmovilizado="",dispocicion="",t_registro="",rev_tecnica="",n_acompañantes="",SOAT="",id_soat="",poliza="",fecha_v_soat="",porta_seguro="",id_seguro="",asignatura="",fecha_vsre="",porta_seguro2="",fecha_vsce="";
        //empresa,nit,matriculado,inmovilizado,dispocicion,t_registro,rev_tecnica,n_acompañantes,SOAT,id_soat,poliza,fecha_v_soat,porta_seguro,id_seguro,asignatura,fecha_vsre,porta_seguro2,fecha_vsce;
        List<DBDetallesV> dev = new Select().from(DBDetallesV.class).queryList();
        for (DBDetallesV a : dev) {
            empresa=a.empresa;
            nit=a.nit;
            matriculado=a.matriculado;
            inmovilizado=a.inmovilizado;
            dispocicion=a.dispocicion;
            t_registro=a.t_registro;
            rev_tecnica=a.rev_tecnica;
            n_acompañantes=a.n_acompañantes;
            SOAT=a.SOAT;
            id_soat=a.id_soat;
            poliza=a.poliza;
            fecha_v_soat=a.fecha_v_soat;
            porta_seguro=a.porta_seguro;
            asignatura=a.asignatura;
            fecha_vsre=a.fecha_vsre;
            porta_seguro2=a.porta_seguro2;
            fecha_vsce=a.fecha_vsce;
        }
        //execute()
        String mismo_cond,nombre2,t_doc,n_doc,clasev,clases,modalidad_t,radioa,fallas,descrip_daño,lugar_impacto;
        List<DBPropietario> pr = new Select().from(DBPropietario.class).queryList();
        for (DBPropietario a : pr) {
            //mismo_cond,nombre2,t_doc,n_doc,clasev,clases,modalidad_t,radioa,fallas,descrip_daño,lugar_impacto
            mismo_cond=a.mismo_cond;
            nombre2=a.nombre;
            t_doc=a.t_doc;
            n_doc=a.n_doc;
            clasev=a.clasev;
            clases=a.clases;
            modalidad_t=a.modalidad_t;
            radioa=a.radioa;
            fallas=a.fallas;
            descrip_daño=a.descrip_daño;
            lugar_impacto=a.lugar_impacto;
        }
        //execute()
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
