/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.TipoMueble;
import com.losalpes.entities.TipoUsuario;
import com.losalpes.entities.Usuario;
import java.util.ArrayList;
import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.InitialContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edgaguil
 */
public class ServicioCarritoMockTest {

    /**
     * Interface con referencia al servicio de carrito de compra
     */
    private IServicioCarritoMockRemote servicio;
    private IServicioPersistenciaMockRemote servicioPersistencia;
    private static final double DELTA = 0.0001;

    @Before
    public void setUp() {
        try {
            Properties env = new Properties();
            env.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            env.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            env.put("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext contexto;
            contexto = new InitialContext(env);
            servicio = (IServicioCarritoMockRemote) contexto.lookup("com.losalpes.servicios.IServicioCarritoMockRemote");
            servicioPersistencia = (IServicioPersistenciaMockRemote) contexto.lookup("com.losalpes.servicios.IServicioPersistenciaMockRemote");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of agregarItem method, of class ServicioCarritoMock.
     */
    @Test
    public void testAgregarItem() throws Exception {
        System.out.println("agregarItem");
        Mueble mueble = new Mueble(1L, "Silla clásica de prueba", "Una confortable silla de prueba con estilo del siglo XIX.", TipoMueble.Interior, 45, "sillaClasicaPrueba", 123);
        servicio.agregarItem(mueble);
    }

    /**
     * Test of getInventario method, of class ServicioCarritoMock.
     */
    @Test
    public void testGetInventario() throws Exception {
        Mueble mueble = new Mueble(2L, "Silla clásica de prueba2", "Una confortable silla de prueba2 con estilo del siglo XIX.", TipoMueble.Interior, 45, "sillaClasicaPrueba2", 1234);
        servicio.agregarItem(mueble);
        ArrayList<Mueble> inventario = servicio.getInventario();
        assertEquals(1, inventario.size());
    }

    /**
     * Test of comprar method, of class ServicioCarritoMock.
     */
    @Test
    public void testComprar() throws Exception {
        Mueble muebleAComprar = new Mueble(3L, "Silla moderna", "Lo último en la moda de interiores. Esta silla le brindará la comodidad e innovación que busca", TipoMueble.Interior, 0, "sillaModerna", 5464);
        Mueble infoMuebleAntesCompra = (Mueble) servicioPersistencia.findById(Mueble.class, muebleAComprar.getReferencia());
        Usuario usuario = new Usuario("client", "clientclient", TipoUsuario.Cliente);
        servicio.agregarItem(muebleAComprar);
        servicio.comprar(usuario);
        Mueble infoMuebleDespuesCompra = (Mueble) servicioPersistencia.findById(Mueble.class, muebleAComprar.getReferencia());
        assertEquals(infoMuebleAntesCompra.getCantidad(), infoMuebleDespuesCompra.getCantidad());
    }

    /**
     * Test of removerItem method, of class ServicioCarritoMock.
     */
    @Test
    public void testRemoverItem() throws Exception {
        ArrayList<Mueble> listadoMueblesCarritoInicial = servicio.getInventario();
        Mueble mueble = new Mueble(4L, "Mesa de jardín", "Una bella mesa para comidas y reuniones al aire libre.", TipoMueble.Exterior, 0, "mesaJardin", 4568);
        servicio.agregarItem(mueble);
        ArrayList<Mueble> listadoMueblesCarritoDespuesAgregar = servicio.getInventario();
        servicio.removerItem(mueble, true);
        ArrayList<Mueble> listadoMueblesCarritoDespuesRemover = servicio.getInventario();
        assertEquals(0, listadoMueblesCarritoInicial.size());
        assertEquals(1, listadoMueblesCarritoDespuesAgregar.size());
        assertEquals(0, listadoMueblesCarritoDespuesRemover.size());
    }

    /**
     * Test of recalcularInventarioTotal method, of class ServicioCarritoMock.
     */
    @Test
    public void testRecalcularInventarioTotal() throws Exception {       
        
        Mueble mueble1 = (Mueble) servicioPersistencia.findById(Mueble.class, 5L);
        Mueble mueble2 = (Mueble) servicioPersistencia.findById(Mueble.class, 6L);
        Mueble mueble3 = (Mueble) servicioPersistencia.findById(Mueble.class, 7L);               
        
        mueble1.setCantidad(1);        
        mueble2.setCantidad(1);        
        mueble3.setCantidad(1);
        
        servicio.agregarItem(mueble1);
        servicio.agregarItem(mueble2);
        servicio.agregarItem(mueble3);       
        
        servicio.recalcularInventarioTotal();
        double precionTotalInventario = servicio.getPrecioTotalInventario();       
        
        assertEquals(mueble1.getPrecio() + mueble2.getPrecio() + mueble3.getPrecio(), precionTotalInventario, DELTA);        
    }
}
