/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.entities.ReporteVentasMes;
import com.losalpes.entities.VentaDia;
import com.losalpes.servicios.IServicioReporte;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author edgaguil
 */
@ManagedBean
@SessionScoped
public class ReporteBean implements Serializable {
    
    @EJB
    private IServicioReporte servicioReporte;
    private ReporteVentasMes reporteVentasMes;
    private BarChartModel modeloReporteVentasGrafico;    
    
    @PostConstruct
    public void init(){
        reporteVentasMes = new ReporteVentasMes();                
        crearModeloVentasGrafico();
    }
    
    public void generarReporteVentasUltimoMes(){        
        reporteVentasMes = servicioReporte.generarReporteVentasAnioMes(new Date());
        boolean flag = false;
    }

    public ReporteVentasMes getReporteVentasMes() {
        return reporteVentasMes;
    }
    
    public BarChartModel getModeloReporteVentasGrafico() {
        return modeloReporteVentasGrafico;
    }

    public void setModeloReporteVentasGrafico(BarChartModel modeloReporteVentasGrafico) {
        this.modeloReporteVentasGrafico = modeloReporteVentasGrafico;
    }
    
    
    private void crearModeloVentasGrafico(){ 
        generarReporteVentasUltimoMes();
        
        ChartSeries ventasMuebles = new ChartSeries();
        ChartSeries ventasTotales = new ChartSeries();
        
        ventasMuebles.setLabel("Ventas Dia");
        for (VentaDia ventaDia : this.reporteVentasMes.getVentasPorDia()) {            
            ventasMuebles.set(String.valueOf(ventaDia.getDia()), ventaDia.getTotalVenta());
        }       
        
        
        modeloReporteVentasGrafico = new BarChartModel();
        modeloReporteVentasGrafico.setAnimate(true);
        modeloReporteVentasGrafico.addSeries(ventasMuebles);                
        
        modeloReporteVentasGrafico.setLegendPosition("ne");        
        
        Axis ejeY = modeloReporteVentasGrafico.getAxis(AxisType.Y);
        ejeY.setLabel("Ventas ($)");
        ejeY.setMin(0);
        ejeY.setMax(2000000);        
        
        Axis ejeX = modeloReporteVentasGrafico.getAxis(AxisType.X);
        ejeX.setLabel("Dia del Mes");
    }
}
