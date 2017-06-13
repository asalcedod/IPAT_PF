package com.uninorte.transdigital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

public class Pendientes extends AppCompatActivity {
    ListView pend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendientes);
        pend = (ListView)findViewById(R.id.list);
        ArrayList<String> pendientes=new ArrayList<String>();
        List<DBEstado> pr = new Select().from(DBEstado.class).queryList();
        for (DBEstado a : pr) {
            pendientes.add(""+a.id);
        }
        ArrayAdapter<String> adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pendientes);
        pend.setAdapter(adaptador1);
    }
}
