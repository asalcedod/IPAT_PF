package com.uninorte.transdigital;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by antonio on 20/04/17.
 */

@Table(database = AppDatabase.class)
public class DBClaseAccidente extends BaseModel {
    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    String choque,volcameinto,caida,ocupante,incendio,otro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoque() {
        return choque;
    }

    public void setChoque(String choque) {
        this.choque = choque;
    }

    public String getVolcameinto() {
        return volcameinto;
    }

    public void setVolcameinto(String volcameinto) {
        this.volcameinto = volcameinto;
    }

    public String getCaida() {
        return caida;
    }

    public void setCaida(String caida) {
        this.caida = caida;
    }

    public String getOcupante() {
        return ocupante;
    }

    public void setOcupante(String ocupante) {
        this.ocupante = ocupante;
    }

    public String getIncendio() {
        return incendio;
    }

    public void setIncendio(String incendio) {
        this.incendio = incendio;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }
}
