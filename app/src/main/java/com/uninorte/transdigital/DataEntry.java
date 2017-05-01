package com.uninorte.transdigital;

import java.io.Serializable;

/**
 * Created by LauryV on 05/03/2017.
 */

//modelo estandar de base de datos


public class DataEntry implements Serializable {

    int id;
    String field1 ;

    //constructor sin parametro
    public DataEntry(){}

    //constructor con los parametros
    public DataEntry(int id, String field1){
        this.id = id;
        this.field1 = field1;

    }
    //constructor con los dos campos de datos
    public DataEntry(String field1){
        this.field1 = field1;

    }


}
