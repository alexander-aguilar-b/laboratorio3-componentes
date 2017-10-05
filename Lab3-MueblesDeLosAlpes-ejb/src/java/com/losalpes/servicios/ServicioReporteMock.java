/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.ReporteVentasMes;
import com.losalpes.entities.VentaDia;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Double totalVentasMes = new Double(0);
        ReporteVentasMes reporteVentasMes = new ReporteVentasMes();
        List<RegistroVenta> registroVentas = servicioPersistencia.findAll(RegistroVenta.class);
        Double totalVentasDia = new Double(0);

        Map<Integer, Double> ventasDia = new HashMap<Integer, Double>();

        for (RegistroVenta registroVenta : registroVentas) {
            Date fechaVentaItem = registroVenta.getFechaVenta();

            if (fechaVentaItem.getYear() == fecha.getYear() && fechaVentaItem.getMonth() == fecha.getMonth()) {

                Calendar calendarioFechaVenta = Calendar.getInstance();
                calendarioFechaVenta.setTime(fechaVentaItem);
                int diaVenta = calendarioFechaVenta.get(Calendar.DAY_OF_MONTH);

                Double totalVentaIndividual = registroVenta.getCantidad() * registroVenta.getProducto().getPrecio();

                if (ventasDia.containsKey(diaVenta)) {
                    totalVentasDia = ventasDia.get(diaVenta);
                    ventasDia.put(diaVenta, totalVentasDia + totalVentaIndividual);
                } else {
                    ventasDia.put(diaVenta, totalVentaIndividual);
                }

                totalVentasMes += registroVenta.getCantidad() * registroVenta.getProducto().getPrecio();
            }
        }

        Calendar calendario = new GregorianCalendar(fecha.getYear(), fecha.getMonth(), fecha.getDay());
        int diasMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        List<VentaDia> ventasPorDia = new ArrayList<VentaDia>();

        for (Map.Entry<Integer, Double> entry : ventasDia.entrySet()) {
            Integer llave = entry.getKey();
            Double valor = entry.getValue();
            ventasPorDia.add(new VentaDia(llave, valor));
        }

        reporteVentasMes.setDiasMes(diasMes);
        reporteVentasMes.setVentasDia(ventasDia);
        reporteVentasMes.setTotalVentasMes(totalVentasMes);
        reporteVentasMes.setVentasPorDia(ventasPorDia);

        return reporteVentasMes;
    }
}
