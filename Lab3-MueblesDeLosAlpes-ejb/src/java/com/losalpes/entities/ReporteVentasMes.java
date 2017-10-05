/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

import java.util.Date;

/**
 *
 * @author edgaguil
 */
public class ReporteVentasMes {
    
    private int diasMes;
    private double totalVentasMes;
    private Date fechaGeneracion; 

    public ReporteVentasMes() {
        this.fechaGeneracion = new Date();
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getDiasMes() {
        return diasMes;
    }

    public void setDiasMes(int diasMes) {
        this.diasMes = diasMes;
    }

    public double getTotalVentasMes() {
        return totalVentasMes;
    }

    public void setTotalVentasMes(double totalVentasMes) {
        this.totalVentasMes = totalVentasMes;
    }
}
