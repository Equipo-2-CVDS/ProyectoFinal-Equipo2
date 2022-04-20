package edu.eci.cvds;

import java.sql.Time;

import com.google.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
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
    ProyectoServices serviciosProyectoTest;

    public ProyectoTest() {
        serviciosProyecto = ProyectoServicesFactory.getInstance().getServiciosProyecto();
        serviciosProyectoTest = ProyectoServicesFactory.getInstance().getServiciosProyectoTesting();
    }

    @Before
    public void setUp() {
        serviciosProyecto = ProyectoServicesFactory.getInstance().getServiciosProyecto();
        serviciosProyectoTest = ProyectoServicesFactory.getInstance().getServiciosProyectoTesting();
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


    @Test
    public void deberiaEncontrarRecurso(){
        try{
            Assert.assertEquals("Asus",serviciosProyecto.getRecurso("Asus").getNombre()); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaEncontrarRecursos(){
        try{
            Assert.assertEquals("Asus",serviciosProyecto.getRecursosDisponibles().get(0).getNombre()); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaEncontrarHorario(){
        try{
            Assert.assertEquals(1,serviciosProyecto.getHorariosDisponibles(1).size()); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}