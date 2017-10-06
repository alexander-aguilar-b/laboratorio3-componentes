/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.TipoDocumento;
import javax.naming.InitialContext;
import java.util.Properties;
import com.losalpes.entities.TipoUsuario;
import com.losalpes.entities.Usuario;
import java.util.Random;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author edgaguil
 */
public class ServicioRegistroMockTest {
    
    /**
     * Interface con referencia al servicio de vendedores en el sistema
     */
    private IServicioRegistroMockRemote servicio;
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
            servicio = (IServicioRegistroMockRemote) contexto.lookup("com.losalpes.servicios.IServicioRegistroMockRemote");
            servicioPersistencia = (IServicioPersistenciaMockRemote) contexto.lookup("com.losalpes.servicios.IServicioPersistenciaMockRemote");
        } 
        catch (Exception e)
        {
            throw new Exception(e.getMessage());           
        }
    }
    
    
    /**
     * Test of registrar method, of class ServicioRegistroMock.
     */
    @Test
    public void testRegistrar() throws Exception {                
        long sufijo = System.currentTimeMillis();
        Usuario usuario = new Usuario("Alexander" + String.valueOf(sufijo), "alex1.", TipoUsuario.Administrador);                
        usuario.setDocumento(sufijo);        
        usuario.setTipoDocumento(TipoDocumento.CC);
        servicio.registrar(usuario);        
        Usuario usuarioRegistrado =(Usuario) servicioPersistencia.findById(Usuario.class, usuario.getLogin());
        assertNotNull(usuarioRegistrado);
    }

    /**
     * Test of eliminarCliente method, of class ServicioRegistroMock.
     */
    @Test
    public void testEliminarCliente() throws Exception {
        long sufijo = System.currentTimeMillis();        
        Usuario usuario = new Usuario("Alexander" + String.valueOf(sufijo), "alex1.", TipoUsuario.Administrador);                        
        usuario.setDocumento(Long.valueOf(sufijo));        
        usuario.setTipoDocumento(TipoDocumento.CC);
        servicio.registrar(usuario); 
        servicio.eliminarCliente(usuario.getLogin());        
        Usuario usuarioRegistrado =(Usuario) servicioPersistencia.findById(Usuario.class, usuario.getLogin());        
        assertNull(usuarioRegistrado);        
    }

    /**
     * Test of darClientes method, of class ServicioRegistroMock.
     */
    /*@Test
    public void testDarClientes() throws Exception {
        
    }*/
    
}
