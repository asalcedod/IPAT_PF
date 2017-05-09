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

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Intro extends AppCompatActivity {

    private TextInputEditText pass;
    private EditText ced;


    private ProgressDialog pDialog;

    // Clase JSONParser
    JSONParser jsonParser = new JSONParser();

    private static final String LOGIN_URL = "https://transitodigital-asalcedod.c9users.io/login.php";

    // La respuesta del JSON es
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


          }

    public void Onclik_Iniciar(View view) {
        showLoginDialog();
        // Intent i = new Intent(this, Login.class);
        //startActivity(i);

    }

    public void Onclick_Registro(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);

    }

    //LOGIN--------------------------------------------------------------------------------
    private void showLoginDialog()
    {
        LayoutInflater li = LayoutInflater.from(this);
        View prompt = li.inflate(R.layout.activity_login, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(prompt);
        final EditText ced = (EditText) prompt.findViewById(R.id.cedula);
        final EditText pass = (TextInputEditText) prompt.findViewById(R.id.textInputEditText);


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        String cedula = ced.getText().toString();
                        String password = pass.getText().toString();

                        if (cedula.length()==0&& password.length()==0){
                            //ced.setError("No Puede Estar Vacio");
                            //pass.setError("No Puede Estar Vacio");
                            Toast.makeText(Intro.this,"Error!... Hay campos vacíos", Toast.LENGTH_LONG).show();
                            showLoginDialog();
                            //showLoginDialog();
                        }
                        else{
                            if (cedula.length()==0) {
                                Toast.makeText(Intro.this,"Error!... Ingrese cedula", Toast.LENGTH_LONG).show();
                                showLoginDialog();
                            }
                            else
                            if (password.length()==0) {
                                Toast.makeText(Intro.this,"Error!... Ingrese contraseña", Toast.LENGTH_LONG).show();
                                showLoginDialog();
                            } else {
                                new Intro.AttemptLogin().execute(cedula,password);
                            }
                        }
                        //---------------------------------
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();

            }
        });

        alertDialogBuilder.show();

    }

    class AttemptLogin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Intro.this);
            pDialog.setMessage("Attempting login...");
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

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    // save user data
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(Intro.this);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("cedula", cedula);
                    edit.commit();
                    // Intent i = new Intent(Login.this, ReadComments.class);
                    Intent i = new Intent(Intro.this, MainActivity.class);
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


}
