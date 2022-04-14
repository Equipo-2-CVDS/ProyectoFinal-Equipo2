package edu.eci.cvds;

import com.google.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ProyectoServicesFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProyectoTest {

    @Inject
    private SqlSession sqlSession;

    ProyectoServices serviciosProyecto;

    public ProyectoTest() {
        serviciosProyecto = ProyectoServicesFactory.getInstance().getServiciosProyecto();
    }

    @Before
    public void setUp() {
        serviciosProyecto = ProyectoServicesFactory.getInstance().getServiciosProyecto();
    }

    @Test
    public void deberiaEncontrarCliente(){
        try{
            Assert.assertTrue(1==serviciosProyecto.buscarUsuario("admin").getId()); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void deberiaEncontrarRol(){
        try{
            Assert.assertEquals("Administrador",serviciosProyecto.getRol(1)); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}