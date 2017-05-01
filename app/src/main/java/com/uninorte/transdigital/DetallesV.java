package com.uninorte.transdigital;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by antonio on 30/04/17.
 */
@Table(database = AppDatabase.class)
public class DetallesV extends BaseModel {
    @PrimaryKey
    int id;
    @Column
    String empresa,nit,matriculado,inmovilizado,dispocicion,t_registro,rev_tecnica,n_acompañantes,SOAT,id_soat,poliza,fecha_v_soat,porta_seguro,id_seguro,asignatura,fecha_vsre,porta_seguro2,fecha_vsce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(String matriculado) {
        this.matriculado = matriculado;
    }

    public String getInmovilizado() {
        return inmovilizado;
    }

    public void setInmovilizado(String inmovilizado) {
        this.inmovilizado = inmovilizado;
    }

    public String getDispocicion() {
        return dispocicion;
    }

    public void setDispocicion(String dispocicion) {
        this.dispocicion = dispocicion;
    }

    public String getT_registro() {
        return t_registro;
    }

    public void setT_registro(String t_registro) {
        this.t_registro = t_registro;
    }

    public String getRev_tecnica() {
        return rev_tecnica;
    }

    public void setRev_tecnica(String rev_tecnica) {
        this.rev_tecnica = rev_tecnica;
    }

    public String getN_acompañantes() {
        return n_acompañantes;
    }

    public void setN_acompañantes(String n_acompañantes) {
        this.n_acompañantes = n_acompañantes;
    }

    public String getSOAT() {
        return SOAT;
    }

    public void setSOAT(String SOAT) {
        this.SOAT = SOAT;
    }

    public String getId_soat() {
        return id_soat;
    }

    public void setId_soat(String id_soat) {
        this.id_soat = id_soat;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public String getFecha_v_soat() {
        return fecha_v_soat;
    }

    public void setFecha_v_soat(String fecha_v_soat) {
        this.fecha_v_soat = fecha_v_soat;
    }

    public String getPorta_seguro() {
        return porta_seguro;
    }

    public void setPorta_seguro(String porta_seguro) {
        this.porta_seguro = porta_seguro;
    }

    public String getId_seguro() {
        return id_seguro;
    }

    public void setId_seguro(String id_seguro) {
        this.id_seguro = id_seguro;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getFecha_vsre() {
        return fecha_vsre;
    }

    public void setFecha_vsre(String fecha_vsre) {
        this.fecha_vsre = fecha_vsre;
    }

    public String getPorta_seguro2() {
        return porta_seguro2;
    }

    public void setPorta_seguro2(String porta_seguro2) {
        this.porta_seguro2 = porta_seguro2;
    }

    public String getFecha_vsce() {
        return fecha_vsce;
    }

    public void setFecha_vsce(String fecha_vsce) {
        this.fecha_vsce = fecha_vsce;
    }
}
