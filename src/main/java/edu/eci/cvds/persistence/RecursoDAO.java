package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Recurso;

public interface RecursoDAO {
    List<Recurso> getRecursosDisponibles() throws PersistenceException;

    Recurso getRecurso(String nombre) throws PersistenceException;

    List<Recurso> getRecursos() throws PersistenceException;

    public Recurso getRecursoPorId(int id) throws PersistenceException;

    public void insertarRecurso(Recurso r) throws PersistenceException;

    public void cancelarRecurso(int id) throws  PersistenceException;
}
