package com.uninorte.transdigital;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Anexos extends Activity implements OnClickListener{
    Button btnBuscar;
    Button btnCam;
    ImageView img;
    Bitmap bmp;
    Intent i;
    final static int cons = 0;
    TextView txtUbicacion;
    String ubicacion = "";


    private void abrirArchivo(){
        try {
            txtUbicacion.setText(ubicacion);
            File f = new File(ubicacion);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String texto = br.readLine();
            String aux = "";
            while(texto != null){
                aux=aux+texto;
                texto = br.readLine();
            }
            br.close();
        } catch (Exception e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anexos);
        init();
    }

    private void init() {
        btnCam = (Button) findViewById(R.id.cam);
        btnCam.setOnClickListener(this);

        img = (ImageView) findViewById(R.id.imagen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void ActCam(View view) {
        int id;
        id= view.getId();
        switch(id){
            case R.id.cam:
                i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,cons);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        int id;
        id= v.getId();
        switch(id){
            case R.id.cam:
                i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,cons);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Bundle ext = data.getExtras();
            bmp = (Bitmap)ext.get("data");
            img.setImageBitmap(bmp);
        }
    }
}
