/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.entities.ReporteVentasMes;
import com.losalpes.servicios.IServicioReporte;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author edgaguil
 */
//@ManagedBean
//@SessionScoped
public class ReporteBean implements Serializable {
    
    @EJB
    private IServicioReporte servicioReporte;
    private ReporteVentasMes reporteVentasMes;
    
    @PostConstruct
    public void init(){
        reporteVentasMes = new ReporteVentasMes();
    }
    
    public void generarReporteVentasUltimoMes(){        
        reporteVentasMes = servicioReporte.generarReporteVentasAnioMes(new Date());
    }

    public ReporteVentasMes getReporteVentasMes() {
        return reporteVentasMes;
    }
}
