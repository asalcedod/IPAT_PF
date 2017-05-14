package com.uninorte.transdigital;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by antonio on 30/04/17.
 */
@Table(database = AppDatabase.class)
public class DBDatosV extends BaseModel {
    @PrimaryKey
    int id;
    @Column
    String placa,remorque,nacionalidad,marca,linea,color,modelo,carroceria,toneladas,n_personas,id_licencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRemorque() {
        return remorque;
    }

    public void setRemorque(String remorque) {
        this.remorque = remorque;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public String getToneladas() {
        return toneladas;
    }

    public void setToneladas(String toneladas) {
        this.toneladas = toneladas;
    }

    public String getN_personas() {
        return n_personas;
    }

    public void setN_personas(String n_personas) {
        this.n_personas = n_personas;
    }

    public String getId_licencia() {
        return id_licencia;
    }

    public void setId_licencia(String id_licencia) {
        this.id_licencia = id_licencia;
    }
}
