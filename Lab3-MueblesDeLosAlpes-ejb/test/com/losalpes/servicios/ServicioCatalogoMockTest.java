/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.TipoMueble;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.InitialContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edgaguil
 */
public class ServicioCatalogoMockTest {
    
    /**
     * Interface con referencia al servicio de carrito de compra
     */
    private IServicioCatalogoMockRemote servicio;
    private IServicioPersistenciaMockRemote servicioPersistencia;    
    
    @Before
    public void setUp() throws Exception {
        try
        {
            Properties env = new Properties();
            env.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            env.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            env.put("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext contexto;
            contexto = new InitialContext(env);            
            servicio = (IServicioCatalogoMockRemote) contexto.lookup("com.losalpes.servicios.IServicioCatalogoMockRemote");
            servicioPersistencia = (IServicioPersistenciaMockRemote) contexto.lookup("com.losalpes.servicios.IServicioPersistenciaMockRemote");
        } 
        catch (Exception e)
        {
            throw new Exception(e.getMessage());           
        }
    }   
    

    /**
     * Test of agregarMueble method, of class ServicioCatalogoMock.
     */
    @Test
    public void testAgregarMueble() throws Exception { 
        List<Mueble> listadoMueblesAntes = servicioPersistencia.findAll(Mueble.class);
        Mueble mueble = new Mueble(); 
        mueble.setNombre("Silla Nueva Catalogo" + String.valueOf(listadoMueblesAntes.size() + 1));
        mueble.setDescripcion("Descripción silla nueva catalogo"+ String.valueOf(listadoMueblesAntes.size() + 1));
        mueble.setTipo(TipoMueble.Interior);
        mueble.setCantidad(100);
        mueble.setPrecio(1000);        
        servicio.agregarMueble(mueble);
        List<Mueble> listadoMueblesDespues = servicioPersistencia.findAll(Mueble.class);
        assertEquals(listadoMueblesAntes.size() + 1 , listadoMueblesDespues.size());        
    }

    /**
     * Test of eliminarMueble method, of class ServicioCatalogoMock.
     */
    @Test
    public void testEliminarMueble() throws Exception {   
        List<Mueble> listadoMueblesAntes = servicioPersistencia.findAll(Mueble.class);
        Mueble mueble = new Mueble(); 
        mueble.setNombre("Silla Nueva Catalogo" + String.valueOf(listadoMueblesAntes.size() + 1));
        mueble.setDescripcion("Descripción silla nueva catalogo"+ String.valueOf(listadoMueblesAntes.size() + 1));
        mueble.setTipo(TipoMueble.Interior);
        mueble.setCantidad(100);
        mueble.setPrecio(1000);        
        
        servicio.agregarMueble(mueble);
        List<Mueble> listadoMueblesDespuesAgregar = servicioPersistencia.findAll(Mueble.class);
        
        servicio.eliminarMueble(listadoMueblesAntes.size() + 1);                
        List<Mueble> listadoMueblesDespuesEliminar = servicioPersistencia.findAll(Mueble.class);
        
        assertEquals(listadoMueblesAntes.size()+ 1, listadoMueblesDespuesAgregar.size());
        assertEquals(listadoMueblesAntes.size(), listadoMueblesDespuesEliminar.size());                
    }

    /**
     * Test of darMuebles method, of class ServicioCatalogoMock.
     */
    @Test
    public void testDarMuebles() throws Exception {
        List<Mueble> muebles = servicio.darMuebles();
        assertNotNull(muebles);
    }

    /**
     * Test of removerEjemplarMueble method, of class ServicioCatalogoMock.
     */
    @Test
    public void testRemoverEjemplarMueble() throws Exception {        
    }    
}
