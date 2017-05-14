package com.uninorte.transdigital;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by antonio on 30/04/17.
 */
@Table(database = AppDatabase.class)
public class DBVictima extends BaseModel {
    @PrimaryKey
    int id;
    @Column
    String detalle_victima,nombre,tdoc,ndoc,nacionalidad,fecha_n,sexo,direc,ciudad,tel,gravedad,examen,aut,ebriagez,gradoE,sustancias,chaleco,casco,cinturon,hospital;

    public int getId() {
        return id;
    }

    public String getDetalle_victima() {
        return detalle_victima;
    }

    public void setDetalle_victima(String detalle_victima) {
        this.detalle_victima = detalle_victima;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTdoc() {
        return tdoc;
    }

    public void setTdoc(String tdoc) {
        this.tdoc = tdoc;
    }

    public String getNdoc() {
        return ndoc;
    }

    public void setNdoc(String ndoc) {
        this.ndoc = ndoc;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFecha_n() {
        return fecha_n;
    }

    public void setFecha_n(String fecha_n) {
        this.fecha_n = fecha_n;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getAut() {
        return aut;
    }

    public void setAut(String aut) {
        this.aut = aut;
    }

    public String getEbriagez() {
        return ebriagez;
    }

    public void setEbriagez(String ebriagez) {
        this.ebriagez = ebriagez;
    }

    public String getGradoE() {
        return gradoE;
    }

    public void setGradoE(String gradoE) {
        this.gradoE = gradoE;
    }

    public String getSustancias() {
        return sustancias;
    }

    public void setSustancias(String sustancias) {
        this.sustancias = sustancias;
    }

    public String getChaleco() {
        return chaleco;
    }

    public void setChaleco(String chaleco) {
        this.chaleco = chaleco;
    }

    public String getCasco() {
        return casco;
    }

    public void setCasco(String casco) {
        this.casco = casco;
    }

    public String getCinturon() {
        return cinturon;
    }

    public void setCinturon(String cinturon) {
        this.cinturon = cinturon;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

}
