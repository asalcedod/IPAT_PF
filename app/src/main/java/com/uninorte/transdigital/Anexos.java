package com.uninorte.transdigital;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.uninorte.transdigital.R.id.imagen;

public class Anexos extends AppCompatActivity implements OnClickListener{
    FloatingActionButton btnCam;
    ImageView img;
    Bitmap bmp;
    Intent i;
    private String foto;
    private static int TAKE_PICTURE = 0;
    int aleatorio = 0;
    File dir;
    private final static String NOMBRE_DIRECTORIO = "IPAT_Digital";
    private final static String GENERADOR = "MisArchivos";
    final static int cons = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anexos);
        File f =  new File(Environment.getExternalStorageDirectory().toString() + File.separator + NOMBRE_DIRECTORIO);
        if(!f.exists()){
            f.mkdir();
        }
        File ficheroPdf = new File(f.getPath() + File.separator + GENERADOR);
        if(!ficheroPdf.exists()){
            ficheroPdf.mkdir();
        }
        img = (ImageView) findViewById(imagen);
        //Floating Button
        btnCam = (FloatingActionButton) findViewById(R.id.camara);
        btnCam.setOnClickListener(this);
        aleatorio = new Integer((int) (Math.random() * 100)).intValue();
        foto = Environment.getExternalStorageDirectory() + File.separator + NOMBRE_DIRECTORIO + File.separator + GENERADOR;
        dir = new File(foto,"imagen"+ aleatorio +".jpg");

        //-------------------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //----------------------------------------------------------------------
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Toast.makeText(Anexos.this, foto, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(foto);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, 0); // 1 para la camara, 2 para la galeria
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //bmp=(Bitmap) data.getExtras().get(MediaStore.EXTRA_OUTPUT);
        //img.setImageBitmap(bmp);
        Bundle ext = data.getExtras();
        bmp = (Bitmap)ext.get("data");
        img.setImageBitmap(bmp);
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(dir);
            bmp.compress(Bitmap.CompressFormat.JPEG, 10, fos);
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        if (dir.exists()) {
            UploaderFoto nuevaTarea = new UploaderFoto();
            nuevaTarea.execute(foto);
        }
        else{
            Toast.makeText(getApplicationContext(), "No se ha realizado la foto", Toast.LENGTH_SHORT).show();
        }
    }

    class UploaderFoto extends AsyncTask<String, String, String> {

        ProgressDialog pDialog;
        String miFoto = "";

        @Override
        protected String doInBackground(String... params) {
            miFoto = params[0];
            try {
                HttpClient httpclient = new DefaultHttpClient();
                httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
                HttpPost httppost = new HttpPost("https://transitodigital-asalcedod.c9users.io/upload.php");
                File file = new File(miFoto);
                MultipartEntity mpEntity = new MultipartEntity();
                ContentBody foto = new FileBody(file);
                mpEntity.addPart("fotoUp", foto);
                httppost.setEntity(mpEntity);
                httpclient.execute(httppost);
                httpclient.getConnectionManager().shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Anexos.this);
            pDialog.setMessage("Subiendo la imagen, espere.");
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
        }
    }
}
