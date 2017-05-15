package com.uninorte.transdigital;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by antonio on 30/04/17.
 */
@Table(database = AppDatabase.class)
public class DBDetallesCond extends BaseModel {
    @PrimaryKey
    int id;
    @Column
    String gravedad;
    @Column
    String examen;
    @Column
    String aut;
    @Column
    String ebriagez;
    @Column
    String gradoE;
    @Column
    String sustancias;
    @Column
    String portalicencia;
    @Column
    String idlicencia;
    @Column
    String categoria;
    @Column
    String estado;

    public String getRestriccion() {
        return restriccion;
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
    }

    @Column
    String restriccion;
    @Column
    String fecha;
    @Column
    String cod_of;
    @Column
    String chaleco;
    @Column
    String casco;
    @Column
    String cinturon;
    @Column
    String hospital;
    @Column
    String descip;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPortalicencia() {
        return portalicencia;
    }

    public void setPortalicencia(String portalicencia) {
        this.portalicencia = portalicencia;
    }

    public String getIdlicencia() {
        return idlicencia;
    }

    public void setIdlicencia(String idlicencia) {
        this.idlicencia = idlicencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCod_of() {
        return cod_of;
    }

    public void setCod_of(String cod_of) {
        this.cod_of = cod_of;
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

    public String getDescip() {
        return descip;
    }

    public void setDescip(String descip) {
        this.descip = descip;
    }
}
