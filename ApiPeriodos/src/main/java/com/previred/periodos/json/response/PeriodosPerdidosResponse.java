/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.previred.periodos.json.response;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Keylis Valles
 */
public class PeriodosPerdidosResponse  implements Serializable {
    
    private Integer id;
    private String fechaCreacion;
    private String fechaFin;
    private List<String> fechasFaltantes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<String> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(List<String> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }

    @Override
    public String toString() {
        return "PeriodosPerdidosResponse{" + "id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin + ", fechasFaltantes=" + fechasFaltantes + '}';
    }
    
}
