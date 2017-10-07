/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Usuario;
import java.util.Properties;
import javax.naming.InitialContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edgaguil
 */
public class ServicioSeguridadMockTest {
    
    private IServicioSeguridadMockRemote servicio;    
    
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
            servicio = (IServicioSeguridadMockRemote) contexto.lookup("com.losalpes.servicios.IServicioSeguridadMockRemote");            
        } 
        catch (Exception e)
        {
            throw new Exception(e.getMessage());           
        }
    }
    
    
    /**
     * Test of ingresar method, of class ServicioSeguridadMock.
     */
    @Test
    public void testIngresar() throws Exception {  
        Usuario usuario = servicio.ingresar("admin", "adminadmin");
        assertNotNull(usuario);        
    }
    
}
