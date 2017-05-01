package com.uninorte.transdigital;

/**
 * Created by LauryV on 05/03/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//CREA LAS TABLAS PARA EL DATAENTRY---------------------------------------------------------------------

public class DatabaseHandler extends SQLiteOpenHelper{

    public String TAG = Constants.TAG;

    //Contacts table name
    public static final String TABLE = "theTable";

    //Contacts Table Colums name
    public static final String KEY_ID ="id";
    public static final String KEY_FIELD1 ="field1";
 //   public static final String KEY_FIELD2 ="field2";

    //DECLARA EL NOMBRE DE LA BASE DE DATOS Y LA VERSION.

    //DataBase Version
    private static final int DATABASE_VERSION =1;
    //DataBase Name
    private static final String DATABASE_NAME="theDataBase";


    //creacion del constructor
    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d(TAG,"DatabaseHandler contructor");
    }

    //METODO Q SE LLAMARA CUANDO SE CREA LA BD
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreateDB ");

        //SENTENCIA SQL PARA LA CREACION DE LA TABLA

        String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_FIELD1 + " INTEGER"
                + ")";

        if (db.isOpen()){
            db.execSQL(CREATE_TABLE);
        }

    }
    //METODO Q SE LLAMARA CUANDO SE ACTUALICE LA BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgradeDB ");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE);
    }

    public SQLiteDatabase getWriteDatabase(){
        //permitira tener el punto de acceso para escribir en la BD
        return super.getWritableDatabase();
    }
}

