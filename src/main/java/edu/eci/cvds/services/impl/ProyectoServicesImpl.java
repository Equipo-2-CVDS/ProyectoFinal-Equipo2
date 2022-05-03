package edu.eci.cvds.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.UsuRecuRese;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.RecursoDAO;
import edu.eci.cvds.persistence.ReservaDAO;
import edu.eci.cvds.persistence.RolesDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.HorarioDAO;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

public class ProyectoServicesImpl implements ProyectoServices {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private RolesDAO rolesDAO;

    @Inject
    private RecursoDAO recursoDAO;

    @Inject
    private HorarioDAO horarioDAO;

    @Inject
    private ReservaDAO reservaDAO;

    @Override
    public Usuario buscarUsuario(String nombre) throws ServicesException {
        try {
            System.out.println(usuarioDAO);
            return usuarioDAO.buscarUsuario(nombre);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando usuario:" + nombre, ex);
        }
    }

    @Override
    public String getRol(int id) throws ServicesException {
        try {
            return rolesDAO.getRol(id);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando rol:" + id, ex);
        }

    }

    public List<Recurso> getRecursosDisponibles() throws ServicesException {
        try {
            return recursoDAO.getRecursosDisponibles();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando recursos", ex);
        }
    }
    @Override
    public void insertarRecurso(Recurso r) throws ServicesException {
        try {
            recursoDAO.insertarRecurso(r);
        } catch (Exception e) {
            throw new ServicesException("Error al insertar recurso", e);
        }
    }

    @Override
    public List<Horario> getHorariosDisponibles(int idRecurso) throws ServicesException {
        try {
            return horarioDAO.getHorariosDisponibles(idRecurso);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando Horario", ex);
        }
    }

    @Override
    public void insertarHorario(Horario h) throws ServicesException {
        try {
            horarioDAO.insertarHorario(h);
        } catch (Exception e) {
            throw new ServicesException("Error al insertar Horario", e);
        }
    }

    @Override
    public Recurso getRecurso(String nombre) throws ServicesException {
        try {
            return recursoDAO.getRecurso(nombre);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando recurso con nombre: " + nombre, ex);
        }
    }

    @Override
    public void insertarReserva(Reserva re) throws ServicesException {
        try {
            reservaDAO.insertarReserva(re);
        } catch (Exception e) {
            throw new ServicesException("Error al insertar Reserva", e);
        }
    }

    @Override
    public List<Reserva> getReservasUsuario(int idUsuario) throws ServicesException {
        try {
            return reservaDAO.getReservasUsuario(idUsuario);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando reservas con id: " + idUsuario, ex);
        }
    }

    @Override
    public List<Reserva> getReservas() throws ServicesException {
        try {
            return reservaDAO.getReservas();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando reservas", ex);
        }
    }

    @Override
    public List<Recurso> getRecursos() throws ServicesException {
        try{
            return recursoDAO.getRecursos();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando los recursos", ex);
    public Horario getHorarioDia(int idRecurso, int idDia) throws ServicesException {
        try {
            return horarioDAO.getHorarioDia(idRecurso, idDia);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando Horario", ex);
        }
    }

    @Override
    public List<Reserva> getReservasRecurso(int idRecurso) throws ServicesException {
        try {
            return reservaDAO.getReservasRecurso(idRecurso);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando reservas con id: " + idRecurso, ex);
        }
    }

    @Override
    public List<UsuRecuRese> getUsuRecuRese() throws ServicesException {
        try {
            List<UsuRecuRese> res = new ArrayList<>();
            List<Reserva> reservas = reservaDAO.getReservas();
            for(Reserva r: reservas){
                Usuario u = buscarUsuarioPorId(r.getIdUsuario());
                Recurso re = getRecursoPorId(r.getIdRecurso());
                String infoRecurso = "Capacidad: " + Integer.toString(re.getCapacidad()) + ", Ubicacion: " + re.getUbicacion() +", Tipo: " + re.getTipo();
                res.add(new UsuRecuRese(re.getNombre(), infoRecurso, r.getFechaSolicitado(), r.getDesde(), r.getHasta(), u.getNombre(), u.getPrograma(), r.getRecurrencia()));
            }

            return res;
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando reservas", ex);
        }
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) throws ServicesException {
        try {
            return usuarioDAO.getUsuarioPorId(id);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando usuario:" + id, ex);
        }
    }

    @Override
    public Recurso getRecursoPorId(int id) throws ServicesException {
        try {
            return recursoDAO.getRecursoPorId(id);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando recurso con id: " + id, ex);
        }
    }

}
