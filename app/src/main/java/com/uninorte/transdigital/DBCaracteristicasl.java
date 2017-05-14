package com.uninorte.transdigital;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by antonio on 26/04/17.
 */

@Table(database = AppDatabase.class)
public class DBCaracteristicasl extends BaseModel {
    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    String area,sector,zona,diseño,condicionc,id_camp1;

    public String getId_camp1() {
        return id_camp1;
    }

    public void setId_camp1(String id_camp1) {
        this.id_camp1 = id_camp1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDiseño() {
        return diseño;
    }

    public void setDiseño(String diseño) {
        this.diseño = diseño;
    }

    public String getCondicionc() {
        return condicionc;
    }

    public void setCondicionc(String condicionc) {
        this.condicionc = condicionc;
    }
}
