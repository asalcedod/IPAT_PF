package com.uninorte.transdigital;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by antonio on 30/04/17.
 */
@Table(database = AppDatabase.class)
public class DBPropietario extends BaseModel {
    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    String mismo_cond,nombre,t_doc,n_doc,clasev,clases,modalidad_t,radioa,fallas,descrip_daño,lugar_impacto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMismo_cond() {
        return mismo_cond;
    }

    public void setMismo_cond(String mismo_cond) {
        this.mismo_cond = mismo_cond;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getT_doc() {
        return t_doc;
    }

    public void setT_doc(String t_doc) {
        this.t_doc = t_doc;
    }

    public String getN_doc() {
        return n_doc;
    }

    public void setN_doc(String n_doc) {
        this.n_doc = n_doc;
    }

    public String getClasev() {
        return clasev;
    }

    public void setClasev(String clasev) {
        this.clasev = clasev;
    }

    public String getClases() {
        return clases;
    }

    public void setClases(String clases) {
        this.clases = clases;
    }

    public String getModalidad_t() {
        return modalidad_t;
    }

    public void setModalidad_t(String modalidad_t) {
        this.modalidad_t = modalidad_t;
    }

    public String getRadioa() {
        return radioa;
    }

    public void setRadioa(String radioa) {
        this.radioa = radioa;
    }

    public String getFallas() {
        return fallas;
    }

    public void setFallas(String fallas) {
        this.fallas = fallas;
    }

    public String getDescrip_daño() {
        return descrip_daño;
    }

    public void setDescrip_daño(String descrip_daño) {
        this.descrip_daño = descrip_daño;
    }

    public String getLugar_impacto() {
        return lugar_impacto;
    }

    public void setLugar_impacto(String lugar_impacto) {
        this.lugar_impacto = lugar_impacto;
    }
}
