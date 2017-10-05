/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

/**
 *
 * @author edgaguil
 */
public class VentaDia {
    
    private Integer dia;
    private Double totalVenta;

    public VentaDia(Integer dia, Double totalVenta) {
        this.dia = dia;
        this.totalVenta = totalVenta;
    }    
    

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }
    
    
    
}
