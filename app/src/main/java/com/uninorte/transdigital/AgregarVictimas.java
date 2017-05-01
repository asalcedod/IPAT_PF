package com.uninorte.transdigital;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class AgregarVictimas extends AppCompatActivity {
    EditText editText1;
    public String TAG = Constants.TAG;
    private boolean customview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_victimas);

        editText1 = (EditText) findViewById(R.id.name);

        Bundle bundle= getIntent().getExtras();


        if (bundle!=null){
            customview=true;
            DataEntry dataEntry= (DataEntry) getIntent().getSerializableExtra("Entryview");
            editText1.setText(dataEntry.field1+"");
        }else{
            customview=false;
        }

        editText1.setFocusable(!customview);
        editText1.setFocusableInTouchMode(!customview);



    }

    //termine hasta el video 3 - creacion de la primera entrada---------------------------
    public void onClickAceptar(View view) {
        if(customview){
            finish();
        }
        if (TextUtils.isEmpty(editText1.getText().toString())){
            editText1.setError("No puede estar vacio");
        }   else {
                DataEntry de = new DataEntry(editText1.getText().toString());
                Intent i = getIntent();
                i.putExtra("entry", de);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        }

}
