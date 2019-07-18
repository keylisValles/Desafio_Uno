/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.previred.periodos.json.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Keylis Valles
 */
public class PeriodosPerdidosRequest implements Serializable {
    
    private Integer id;
     private Date fechaCreacion;
    private Date fechaFin;
    private List<Date> fechas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Date> getFechas() {
        return fechas;
    }

    public void setFechas(List<Date> fechas) {
        this.fechas = fechas;
    }

    @Override
    public String toString() {
        return "PeriodosPerdidosRequest{" + "id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin + ", fechas=" + fechas + '}';
    }

}
