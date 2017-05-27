package com.uninorte.transdigital;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Intro extends AppCompatActivity {

    //DECLARACION DE VARIABLES--------------------------------------------

    //LOGIN Y REGISTRO
    private TextInputEditText pass;
    private EditText ced;
    // Progress Dialog
    private ProgressDialog pDialog;
    // Clase JSONParser
    JSONParser jsonParser = new JSONParser();
    //ids---// La respuesta del JSON es
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    //LOGIN
    private static final String LOGIN_URL = "https://transitodigital-asalcedod.c9users.io/login.php";

    //REGISTRO
    EditText user;
    //si lo trabajan de manera local en xxx.xxx.x.x va su ip local
    // private static final String REGISTER_URL = "http://xxx.xxx.x.x:1234/cas/register.php";

    //testing on Emulator:
    private static final String REGISTER_URL = "https://transitodigital-asalcedod.c9users.io/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


          }

    public void Onclik_Iniciar(View view) {
        showLoginDialog();
    }

    public void Onclick_Registro(View view) {
        showRegisterDialog();
    }

    //LOGIN--------------------------------------------------------------------------------
    private void showLoginDialog()
    {
        LayoutInflater li = LayoutInflater.from(this);
        final View prompt = li.inflate(R.layout.activity_login, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(prompt);

        final EditText ced = (EditText) prompt.findViewById(R.id.cedula);
        final EditText pass = (TextInputEditText) prompt.findViewById(R.id.textInputEditText);

        //nuevo

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Ok", null) //Set to null. We override the onclick
                .setNegativeButton("Cancelar", null);

        AlertDialog alertDialog=alertDialogBuilder.create();
        //

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cedula = ced.getText().toString();
                        String password = pass.getText().toString();
                        if (TextUtils.isEmpty(cedula)||	TextUtils.isEmpty(password)){

                            Toast.makeText(Intro.this,"Cédula o contraseña no puede estar vacío", Toast.LENGTH_LONG).show();
//                          //ced.setError("No Puede Estar Vacio");
                            //pass.setError("No Puede Estar Vacio");

                        }
                        else{
                            new Intro.AttemptLogin().execute(cedula,password);


                        }
                        //---------------------------------
                    }
                });
            }
        });
        alertDialog.show();
        //
    }


    class AttemptLogin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Intro.this);
            pDialog.setMessage("Iniciando sesión...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            String cedula = args[0];
            String password = args[1];

            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("cedula", cedula));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                        params);
                if(json != null) {
                    // check your log for json response
                    Log.d("Login attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    // save user data
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(Intro.this);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("cedula", cedula);
                    edit.commit();
                    // Intent i = new Intent(Login.this, ReadComments.class);
                    Intent i = new Intent(Intro.this, Comenzar.class);
                    // Intent i = new Intent(Login.this, Cond_Vehi_Prop.class);


                    finish();
                    startActivity(i);
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
            if (file_url != null) {
                Toast.makeText(Intro.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }
    //END LOGIN-----------------------------------------------------------------------------------


    //REGISTER-----------------------------------------------------------------------------------
    private void showRegisterDialog() {
        LayoutInflater li = LayoutInflater.from(this);
        final View prompt = li.inflate(R.layout.activity_register, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(prompt);

       final EditText ced = (EditText) prompt.findViewById(R.id.cedula);
        final EditText user = (EditText) prompt.findViewById(R.id.username);
        final EditText pass = (TextInputEditText) prompt.findViewById(R.id.password);


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Ok", null) //Set to null. We override the onclick
                .setNegativeButton("Cancelar", null);
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cedula = ced.getText().toString();
                        String username = user.getText().toString();
                        String password = pass.getText().toString();

                        if (TextUtils.isEmpty(cedula)|| TextUtils.isEmpty(username)||	TextUtils.isEmpty(password)){
                            Toast.makeText(Intro.this,"Hay campos vacíos, verifique e intentelo de nuevo", Toast.LENGTH_LONG).show();

                        }else{
                        new CreateUser().execute(cedula, username, password);
                        }

                    }
                });
            }
        });
        alertDialog.show();

    }
    class CreateUser extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Intro.this);
            pDialog.setMessage("Creando usuario...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String cedula = args[0];
            String username = args[1];
            String password = args[2];

            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("cedula", cedula));
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        REGISTER_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("User Created!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
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
            if (file_url != null) {
                Toast.makeText(Intro.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }
    //END REGISTER-------------------------------------------------------------------------------

}
