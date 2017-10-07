/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.TipoMueble;
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
public class ServicioPersistenciaMockTest {
    
    private IServicioPersistenciaMockRemote servicio;    
    
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
            servicio = (IServicioPersistenciaMockRemote) contexto.lookup("com.losalpes.servicios.IServicioPersistenciaMockRemote");            
        } 
        catch (Exception e)
        {
            throw new Exception(e.getMessage());           
        }
    }   
        
    /**
     * Test of create method, of class ServicioPersistenciaMock.
     */
    //@Test
    public void testCreate() throws Exception {
        long sufijo = System.currentTimeMillis();
        List<Mueble> listadoMueblesAntes = servicio.findAll(Mueble.class);        
        Mueble mueble = new Mueble();         
        mueble.setNombre("Silla Nueva Catalogo" + String.valueOf(sufijo));
        mueble.setDescripcion("Descripción silla nueva catalogo");
        mueble.setTipo(TipoMueble.Interior);
        mueble.setCantidad(100);
        mueble.setPrecio(1000);        
        
        servicio.create(mueble);        
        List<Mueble> listadoMueblesDespues = servicio.findAll(Mueble.class);        
        assertEquals(listadoMueblesAntes.size()+ 1, listadoMueblesDespues.size());
    }

    /**
     * Test of update method, of class ServicioPersistenciaMock.
     */
    //@Test
    public void testUpdate() throws Exception {  
        long sufijo = System.currentTimeMillis();
        List<Mueble> listadoMueblesAntes = servicio.findAll(Mueble.class);        
        Mueble mueble = new Mueble();         
        mueble.setNombre("Silla Nueva Catalogo" + String.valueOf(sufijo));
        mueble.setDescripcion("Descripción silla nueva catalogo");
        mueble.setTipo(TipoMueble.Interior);
        mueble.setCantidad(100);
        mueble.setPrecio(1000);        
        
        servicio.create(mueble);    
        Mueble muebleAntesActualizacion = (Mueble)servicio.findById(Mueble.class, Long.valueOf(listadoMueblesAntes.size() + 1));        
        muebleAntesActualizacion.setCantidad(10);        
        servicio.update(muebleAntesActualizacion);       
        Mueble muebleDespuesActualizacion = (Mueble)servicio.findById(Mueble.class, Long.valueOf(listadoMueblesAntes.size() + 1));
        
        assertEquals(muebleAntesActualizacion.getCantidad(), muebleDespuesActualizacion.getCantidad());
    }

    /**
     * Test of delete method, of class ServicioPersistenciaMock.
     */
    @Test
    public void testDelete() throws Exception { 
        
        long sufijo = System.currentTimeMillis();
        List<Mueble> listadoMueblesAntes = servicio.findAll(Mueble.class);        
        Mueble mueble = new Mueble();         
        mueble.setNombre("Silla Nueva Catalogo" + String.valueOf(sufijo));
        mueble.setDescripcion("Descripción silla nueva catalogo");
        mueble.setTipo(TipoMueble.Interior);
        mueble.setCantidad(100);
        mueble.setPrecio(1000);        
        
        servicio.create(mueble);            
        
        List<Mueble> listadoMueblesDespuesCreacion = servicio.findAll(Mueble.class);                
        Mueble muebleAEliminar = (Mueble)servicio.findById(Mueble.class, Long.valueOf(listadoMueblesAntes.size() + 1));        
        
        servicio.delete(muebleAEliminar);
        List<Mueble> listadoMueblesDespuesEliminacion = servicio.findAll(Mueble.class);                
        
        Mueble muebleEliminado = (Mueble)servicio.findById(Mueble.class, Long.valueOf(listadoMueblesAntes.size() + 1));        
        
        assertEquals(listadoMueblesAntes.size() + 1, listadoMueblesDespuesCreacion.size());
        assertEquals(listadoMueblesAntes.size(), listadoMueblesDespuesEliminacion.size());
        assertNull(muebleEliminado);        
    }

    
}
