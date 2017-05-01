package com.uninorte.transdigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Victimas extends AppCompatActivity {

    private ListView listView;
    private String TAG = "ElTagListView";
    private DataEntryDAO mDataEntryDao;
    private ArrayList<DataEntry> Entries;
    private CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victimas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
                Intent i = new Intent(Victimas.this,AgregarVictimas.class);
                startActivityForResult(i,1);

            }
        });

        //Agregar victimas----------------------------------------------
        //video 4- LisAll y borrado
        listView = (ListView) findViewById(R.id.ListView);
        //

        mDataEntryDao = new DataEntryDAO(this);

        Entries = mDataEntryDao.getAllEntrys();
        //  List<DataEntry> entryList = mDataEntryDao.getAllEntries();

        customAdapter = new CustomAdapter(this,Entries);

        listView.setAdapter(customAdapter);

        //PARA CUANDO SE DA CLICK- IDENTIFICAR CUAL SE LE DIO
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView,View v,int position, long l) {
                DataEntry dataEntry = (DataEntry) listView.getItemAtPosition(position);
                Log.d(TAG,"Click en Botton "+dataEntry.id);
                Intent iview= new Intent(Victimas.this,AgregarVictimas.class);
                Bundle mBundle= new Bundle();
                mBundle.putSerializable("Entryview",dataEntry);
                iview.putExtras(mBundle);
                startActivity(iview);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==1){
            if(resultCode== Activity.RESULT_OK){
                DataEntry de= (DataEntry) data.getSerializableExtra("entry");
                mDataEntryDao.addDataEntry(de);
                Entries = mDataEntryDao.getAllEntrys();
                customAdapter.setData(Entries);
                ((CustomAdapter)listView.getAdapter()).notifyDataSetChanged();
            }
        }


    }

}
