package com.uninorte.transdigital;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Campo1 extends Activity implements View.OnClickListener , ActivityCompat.OnRequestPermissionsResultCallback {
    private RadioGroup rdgGrupo;
    private static final String TAG = "LogsAndroid";
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1 ;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION_EXTRA_COMMANDS = 2;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 3;

    //Fecha y Hora...............................................................................
    EditText name, date, hour;
    long ahora = System.currentTimeMillis();
    Date fecha = new Date(ahora);
    DateFormat df = new SimpleDateFormat("dd/MM/yy");
    String salida1 = df.format(fecha);
    Date hora = new Date(ahora);
    DateFormat dh = new SimpleDateFormat("HH:mm:ss");
    String salida2 = dh.format(hora);
    //Localizacion------------------------------------------------------------------------------
    String ubicacion = "Ubicacion";
    String latitud = "";
    String longitud = "";
    String gravedad = "";
    TextView mensaje1;
    private Button mRegister;
    Intent it;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //testing on Emulator:
    private static final String REGISTER_URL = "https://transitodigital-asalcedod.c9users.io/form.php";
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
//--------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campo1);
        it = new Intent(this, Cond_Vehi_Prop.class);
        rdgGrupo = (RadioGroup) findViewById(R.id.rb);
        rdgGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.radioButton4) {
                    Log.d(TAG, "La gravedad del accidente es con muertos");
                    gravedad = "Muertos";
                } else if (checkedId == R.id.radioButton5) {
                    Log.d(TAG, "La gravedad del accidente es con heridos");
                    gravedad = "Heridos";
                } else if (checkedId == R.id.radioButton6) {
                    Log.d(TAG, "La gravedad del accidente es con solo daños");
                    gravedad = "Solo Daños";
                }



            }

        });

        //NUEVO..................................................................................
        name = (EditText) findViewById(R.id.name);
        date = (EditText) findViewById(R.id.date);
        hour = (EditText) findViewById(R.id.hour);
        date.setText(salida1);
        hour.setText(salida2);
        String nombre = name.getText().toString();
        String fecha = salida1;
        String hora = salida2;
        mRegister = (Button) findViewById(R.id.next);
        mRegister.setOnClickListener(this);
        //
        mensaje1 = (TextView) findViewById(R.id.mensaje1);

		/* Uso de la clase LocationManager para obtener la localizacion del GPS */
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Ubica Local = new Ubica();
        Local.setLocaliza(this);
        int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS);
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
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                (LocationListener) Local);
        mensaje1.setText(ubicacion);
    }
    //-----------------------------------------

    //-----------------------------------------


    boolean validar(EditText name, String a){
        boolean sw = false;
        if(!a.equals("")){
            sw=true;
        }
        return sw;
    }
    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    ubicacion = "Mi direccion es: \n"
                            + DirCalle.getAddressLine(0);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public class Ubica implements LocationListener {
        Campo1 localiza;

        public Campo1 getLocaliza() {
            return localiza;
        }

        public void setLocaliza(Campo1 localiza) {
            this.localiza = localiza;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            latitud=""+loc.getLatitude();
            longitud=""+loc.getLongitude();
            this.localiza.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            mensaje1.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            mensaje1.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //
        }

    }

    public boolean valid(EditText cam, String campo){
        if (TextUtils.isEmpty(campo)){
            return false;
        }else{
            return true;
        }
    }

    public void onClick(View view) {
        ArrayList<Boolean> data = new ArrayList<>();
        Act1 act1=new Act1();
        String mname = name.getText().toString();
        String mdate = date.getText().toString();
        String mhour = hour.getText().toString();
        data.add(validar(name,mname));
        data.add(validar(date,mdate));
        data.add(validar(hour,mhour));
        int cont=0;
        boolean sw=true;
        while(cont<data.size()){
            if(data.get(cont)==false){
                sw=false;
                break;
            }
            cont++;
        }if(sw == true && !TextUtils.isEmpty(latitud) && !TextUtils.isEmpty(longitud) && !TextUtils.isEmpty(gravedad) && !TextUtils.isEmpty(salida1) && !TextUtils.isEmpty(salida2)){
            act1.setOt(mname);
            act1.setLatitud(latitud);
            act1.setLongitud(longitud);
            act1.setUbicacion(ubicacion);
            act1.setGravedad(gravedad);
            act1.setA_fecha(salida1);
            act1.setA_hora(salida2);
            act1.setR_fecha(mdate);
            act1.setR_hora(mhour);
            act1.save();
            Log.d("guardando","Saving...");
            /*List<ClaseAccidente> c = new Select().from(ClaseAccidente.class).queryList();
            for (ClaseAccidente ca : c) {
                Toast.makeText(this, ca.a, Toast.LENGTH_LONG).show();
            }*/

            startActivity(it);
            //new Campo1.Addform().execute(mname,mdate,mhour);
        }else{
            Toast.makeText(this,"Existen campos sin completar.",Toast.LENGTH_SHORT).show();
        }
    }
    /*class Addform extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Campo1.this);
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
            String name = args[0];
            String date = args[1];
            String hour = args[2];

            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("date", date));
                params.add(new BasicNameValuePair("hour", hour));

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
                    //finish();
                    startActivity(it);
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
                Toast.makeText(Campo1.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }*/
    //------------------------------------------------------------------------------------------------------------

    public void onClick_Informe2(View view) {
        //se va al campo 2, para continuar con el informe-- El campo 2 es Cond_Veh_Prop
        Intent i = new Intent(this, Cond_Vehi_Prop.class);
        startActivity(i);
    }

    public void onClick_Ubicacion(View view) {
        mensaje1.setText(ubicacion);
        if(!latitud.equals("") && !longitud.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea confirmar la ubicacion desde el Mapa?")
                    .setTitle("Advertencia")
                    .setCancelable(false)
                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                    .setPositiveButton("Continuar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(Campo1.this, Mapa.class);
                                    i.putExtra("latitud", latitud);
                                    i.putExtra("longitud", longitud);
                                    i.putExtra("ubicacion",ubicacion);
                                    startActivityForResult(i,1);
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();


        }
        //MANEJO DE las imagenes
        //Intent i = new Intent(this, ReadComments.class);
        // startActivity(i);
    }


    public void onClick_lugar(View view) {
        //se va a la pestaña para las caracteristicas del lugar
        Intent i = new Intent(this, Caracteristicas_Lugar.class);
        startActivity(i);
    }

    public void onClick_CaracteVias(View view) {
        //se va a la pestañana para las caracteristicas de las vias
        Intent i = new Intent(this, Caracteristicas_Vias.class);
        startActivity(i);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if (resultCode==RESULT_OK){
                ubicacion=data.getStringExtra("ubicacion");
                latitud=data.getStringExtra("latitud");
                longitud=data.getStringExtra("longitud");
                mensaje1.setText(ubicacion);
            }
        }
    }

}
