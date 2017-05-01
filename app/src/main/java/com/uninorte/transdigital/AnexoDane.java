package com.uninorte.transdigital;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by antonio on 1/05/17.
 */

@Table(database = AppDatabase.class)
public class AnexoDane extends BaseModel {
    @PrimaryKey
    int id_dane;
    @Column
    String ciudad,departamento,oficina_transito;

    public int getId_dane() {
        return id_dane;
    }

    public void setId_dane(int id_dane) {
        this.id_dane = id_dane;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getOficina_transito() {
        return oficina_transito;
    }

    public void setOficina_transito(String oficina_transito) {
        this.oficina_transito = oficina_transito;
    }
}
