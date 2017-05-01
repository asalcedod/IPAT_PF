package com.uninorte.transdigital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by LauryV on 05/03/2017.
 */

public class DataEntryDAO {

    public String TAG = Constants.TAG;
    private Context context;
    private DatabaseHandler mDbHander;
    private SQLiteDatabase mDataBase;

    public DataEntryDAO(Context context){
        this.context = context;
        mDbHander = new DatabaseHandler(context);

        open();
    }

    //metodo para abrir y permitir escribir en la BD
    private void open (){
        mDataBase = mDbHander.getWriteDatabase();
    }

    public void close(){
        mDbHander.close();
    }


    //METODOS PARA PODER ADMINISTRAR LA TABLA

    //agregar otra entrada
    public long addDataEntry(DataEntry entry){
        //escribir en la BD
        ContentValues values = new ContentValues();
        //llave y el valor
        values.put(DatabaseHandler.KEY_FIELD1,entry.field1);
        //values.put(DatabaseHandler.KEY_FIELD2,entry.field2);

        //indice q retornara la BD cuando haga el insert
        long index = mDataBase.insert(DatabaseHandler.TABLE,null,values);
        Log.d(TAG,"addDataEntry return "+index);
        return index;
    }

    //Getting single entry
    public DataEntry GetEntryDAO(int id){
        Log.d(TAG, "getDataEntry "+id);
        Cursor cursor = mDataBase.query(
                DatabaseHandler.TABLE,
                new String[]{DatabaseHandler.KEY_ID,DatabaseHandler.KEY_FIELD1},
                DatabaseHandler.KEY_ID + "=? ",
                new String[] { String.valueOf(id) }, null, null, null, null

        );
        if (cursor != null)
            cursor.moveToFirst();
        DataEntry entry = new DataEntry(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        cursor.close();
        return entry;

    }
    // Getting All Entries
    public ArrayList<DataEntry> getAllEntrys() {
        ArrayList<DataEntry> Entrylist = new ArrayList<>();


       // List<DataEntry> entryList = new ArrayList<DataEntry>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE;

      Cursor cursor = mDataBase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataEntry entry = new DataEntry();
                entry.id=(Integer.parseInt(cursor.getString(0)));
                entry.field1=(cursor.getString(1));
               // entry.field2=(Integer.parseInt(cursor.getString(2)));
                // Adding entryList to list
                Entrylist.add(entry);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return Entrylist;
    }

    //Getting entry Count
    public int getEntryCount(){
        int count;
        Log.d(TAG, "getEntryCount ");
        String countQuery = "SELECT  * FROM " + DatabaseHandler.TABLE;
        Cursor cursor = mDataBase.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        //  return count
        return count;
    }

    public int updateEntry(DataEntry Entry) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_FIELD1, Entry.field1);
        //values.put(DatabaseHandler.KEY_FIELD2, Entry.field2);

        // updating row
        return mDataBase.update(DatabaseHandler.TABLE, values, DatabaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(Entry.id) });
    }

    public void deleteEntry(DataEntry Entry) {

        mDataBase.delete(DatabaseHandler.TABLE, DatabaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(Entry.id) });
    }


}

