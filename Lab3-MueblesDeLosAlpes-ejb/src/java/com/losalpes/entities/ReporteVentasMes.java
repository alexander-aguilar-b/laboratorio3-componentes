/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author edgaguil
 */
public class ReporteVentasMes {
    
    private int diasMes;
    private double totalVentasMes;
    private Date fechaGeneracion;     
    private Map<Integer, Double> ventasDia;
    private List<VentaDia> ventasPorDia;

    public List<VentaDia> getVentasPorDia() {
        return ventasPorDia;
    }

    public void setVentasPorDia(List<VentaDia> ventasPorDia) {
        this.ventasPorDia = ventasPorDia;
    }

    public Map<Integer, Double> getVentasDia() {
        return ventasDia;
    }

    public void setVentasDia(Map<Integer, Double> ventasDia) {
        this.ventasDia = ventasDia;
    }
    

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
