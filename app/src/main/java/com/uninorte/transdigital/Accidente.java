package com.uninorte.transdigital;

import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by antonio on 20/04/17.
 */
@Table(database = AppDatabase.class)
public class Accidente extends BaseModel{
    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    String ot,gravedad,latitud,longitud,ubicacion,a_fecha,a_hora,r_fecha,r_hora,accidente,caracteristicasl;
    @Column @Nullable
    String choque,objetof;

    public String getChoque() {
        return choque;
    }

    public void setChoque(String choque) {
        this.choque = choque;
    }

    public String getObjetof() {
        return objetof;
    }

    public void setObjetof(String objetof) {
        this.objetof = objetof;
    }

    public String getCaracteristicasl() {
        return caracteristicasl;
    }

    public void setCaracteristicasl(String caracteristicasl) {
        this.caracteristicasl = caracteristicasl;
    }

    public String getAccidente() {
        return accidente;
    }

    public void setAccidente(String accidente) {
        this.accidente = accidente;
    }



    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getA_fecha() {
        return a_fecha;
    }

    public void setA_fecha(String a_fecha) {
        this.a_fecha = a_fecha;
    }

    public String getA_hora() {
        return a_hora;
    }

    public void setA_hora(String a_hora) {
        this.a_hora = a_hora;
    }

    public String getR_fecha() {
        return r_fecha;
    }

    public void setR_fecha(String r_fecha) {
        this.r_fecha = r_fecha;
    }

    public String getR_hora() {
        return r_hora;
    }

    public void setR_hora(String r_hora) {
        this.r_hora = r_hora;
    }

}
