/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import javax.naming.InitialContext;
import java.util.Properties;
import com.losalpes.entities.TipoUsuario;
import com.losalpes.entities.Usuario;
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
        //System.out.println("registrar");
        
        Usuario u = new Usuario("Alexander", "alex1.", TipoUsuario.Administrador);        
        u.setDocumento(1L);
        //Usuario u = null;
        
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();        
        //IServicioRegistroMockRemote instance = (IServicioRegistroMockRemote)container.getContext().lookup("java:global/classes/ServicioRegistroMock");
        servicio.registrar(u);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarCliente method, of class ServicioRegistroMock.
     */
    //@Test
    public void testEliminarCliente() throws Exception {
        System.out.println("eliminarCliente");
        /*String login = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        IServicioRegistroMockRemote instance = (IServicioRegistroMockRemote)container.getContext().lookup("java:global/classes/ServicioRegistroMock");
        instance.eliminarCliente(login);
        container.close();
        */
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of darClientes method, of class ServicioRegistroMock.
     */
    //@Test
    public void testDarClientes() throws Exception {
        /*System.out.println("darClientes");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        IServicioRegistroMockRemote instance = (IServicioRegistroMockRemote)container.getContext().lookup("java:global/classes/ServicioRegistroMock");
        List<Usuario> expResult = null;
        List<Usuario> result = instance.darClientes();
        assertEquals(expResult, result);
        container.close();*/
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
