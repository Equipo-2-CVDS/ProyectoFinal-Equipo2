package edu.eci.cvds.services.impl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.RecursoDAO;
import edu.eci.cvds.persistence.RolesDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.HorarioDAO;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

public class ProyectoServicesImpl implements ProyectoServices{

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private RolesDAO rolesDAO;

    @Inject
    private RecursoDAO recursoDAO;

    @Inject
    private HorarioDAO horarioDAO;

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
        try{
            return rolesDAO.getRol(id);
        } catch(PersistenceException ex){
            throw new ServicesException("Error buscando rol:" + id, ex);
        }
        
    }

    public List<Recurso> getRecursosDisponibles() throws ServicesException{
        try{
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
        try{
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
        try{
            return recursoDAO.getRecurso(nombre);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando recurso con nombre: "+nombre, ex);
        }
    }

    @Override
    public List<Recurso> getRecursos() throws ServicesException {
        try{
            return recursoDAO.getRecursos();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error buscando los recursos", ex);
        }
    }

}
