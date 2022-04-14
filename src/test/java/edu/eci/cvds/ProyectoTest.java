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
        serviciosProyecto = ProyectoServicesFactory.getInstance().getServiciosProyectoTesting();
    }

    @Before
    public void setUp() {
        serviciosProyecto = ProyectoServicesFactory.getInstance().getServiciosProyectoTesting();
    }

    @Test
    public void deberiaEncontrarCliente(){
        try{
            serviciosProyecto.buscarUsuario("prueba");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}