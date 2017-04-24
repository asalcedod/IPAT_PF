package com.uninorte.transdigital;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Anexos extends AppCompatActivity implements OnClickListener{
    FloatingActionButton btnCam;
    ImageView img;
    Bitmap bmp;
    Intent i;
    final static int cons = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anexos);

        //Floating Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.camara);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                init();
            }
        });

    }

    private void init() {
        btnCam = (FloatingActionButton) findViewById(R.id.camara);
        btnCam.setOnClickListener(this);

        img = (ImageView) findViewById(R.id.imagen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public void onClick(View v) {
        int id;
        id= v.getId();
        switch(id){
            case R.id.camara:
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
