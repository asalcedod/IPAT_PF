package com.uninorte.transdigital;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by antonio on 30/04/17.
 */
@Table(database = AppDatabase.class)
public class DatosV extends BaseModel {
    @PrimaryKey
    int id;
    @Column
    String gravedad,examen,aut,ebriagez,gradoE,sustancias,portalicencia,idlicencia,categoria,cod_of,chaleco,casco,cinturon,hospital,descip;
}
