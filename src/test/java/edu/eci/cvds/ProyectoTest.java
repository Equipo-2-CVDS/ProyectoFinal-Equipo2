package edu.eci.cvds;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ProyectoServicesFactory;
import edu.eci.cvds.persistence.RecursoDAO;
import edu.eci.cvds.persistence.ReservaDAO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.Timestamp;

import static org.mockito.Mockito.doAnswer;

public class ProyectoTest {

    @Mock
    private ReservaDAO reservaDAO;

    @InjectMocks
    private ProyectoServices mockitoService;

    private Reserva reservaMockito;

    @BeforeEach
    void setUpMockito() {
        MockitoAnnotations.initMocks(this);
    }

    ProyectoServices serviciosProyecto;
    ProyectoServices serviciosProyectoTest;

    public ProyectoTest() {
        serviciosProyecto = ProyectoServicesFactory.getInstance().getServiciosProyecto();
        serviciosProyectoTest = ProyectoServicesFactory.getInstance().getServiciosProyectoTesting();
    }

    @Before
    public void setUp() {
        mockitoService = ProyectoServicesFactory.getInstance().getServiciosProyecto();
        MockitoAnnotations.initMocks(this);

        Timestamp time = new Timestamp(System.currentTimeMillis());
        reservaMockito = new Reserva(1, 99, time, time, 0, false);

        serviciosProyecto = ProyectoServicesFactory.getInstance().getServiciosProyecto();
        serviciosProyectoTest = ProyectoServicesFactory.getInstance().getServiciosProyectoTesting();
    }

    @Test
    public void deberiaEncontrarRol() {
        try {
            Assert.assertEquals("Administrador", serviciosProyecto.getRol(1));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarUsuario() {
        try {
            Assert.assertEquals(1, serviciosProyecto.buscarUsuario("admin").getId());
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarRecurso() {
        try {
            Assert.assertEquals("Asus", serviciosProyecto.getRecurso("Asus").getNombre());
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarUsuRecuRese() {
        try {
            Assert.assertEquals("Asus", serviciosProyecto.getUsuRecuRese(2).getNombreRecurso());
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarRecursos() {
        try {
            Assert.assertTrue(serviciosProyecto.getRecursosDisponibles().size() > 0);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarHorario() {
        try {
            Assert.assertEquals(1, serviciosProyecto.getHorariosDisponibles(1).size());
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarReservaPorId() {
        try {
            Assert.assertEquals(2, serviciosProyecto.getReservaPorId(2).getId());
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarReservas() {
        try {
            Assert.assertTrue(serviciosProyecto.getReservas().size() > 0);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarReservaIdRecurso() {
        try {
            Assert.assertTrue(serviciosProyecto.getReservasRecurso(1).size() > 0);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarReservaIdUsuario() {
        try {
            Assert.assertTrue(serviciosProyecto.getReservasUsuario(2).size() > 0);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarTodosLosRecursos() {
        try {
            Assert.assertTrue(serviciosProyecto.getRecursos().size() > 0);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarHorarioPorIds() {
        try {
            Horario horario = serviciosProyecto.getHorarioDia(1, 1);
            Assert.assertEquals(7, horario.getDesde().toLocalTime().getHour());
            Assert.assertEquals(19, horario.getHasta().toLocalTime().getHour());
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaCancelarReserva() {
        try {
            doAnswer(new Answer<Void>() {
                @Override
                public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                    reservaMockito.setEstado(true);
                    return null;
                }
            }).when(reservaDAO).cancelarReserva(1, false);
            mockitoService.cancelarReserva(1, false);
            Assert.assertEquals(true, reservaMockito.getEstado());
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deberiaEncontrarTablaReportes() {
        try {
            Assert.assertTrue(serviciosProyecto.getTable().size() > 0);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

}