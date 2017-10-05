/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.ReporteVentasMes;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author edgaguil
 */
@Stateless
public class ServicioReporteMock implements IServicioReporte {

    @EJB
    private IServicioPersistenciaMockLocal servicioPersistencia;
    
    @Override
    public ReporteVentasMes generarReporteVentasAnioMes(Date fecha) {
        
        Double totalVentas = new Double(0);        
        ReporteVentasMes reporteVentasMes = new ReporteVentasMes();        
        List<RegistroVenta> registroVentas = servicioPersistencia.findAll(RegistroVenta.class);
        
        for (RegistroVenta registroVenta : registroVentas) {
            Date fechaVentaItem = registroVenta.getFechaVenta();
            
            if (fechaVentaItem.getYear() == fecha.getYear() && fechaVentaItem.getMonth() == fecha.getMonth()) {
                totalVentas += registroVenta.getCantidad() * registroVenta.getProducto().getPrecio();
            }
        }
        
        Calendar calendario = new GregorianCalendar(fecha.getYear(), fecha.getMonth(), fecha.getDay());
        int diasMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        reporteVentasMes.setDiasMes(diasMes);
        reporteVentasMes.setTotalVentasMes(totalVentas);
        
        return reporteVentasMes;  
    }   
}
