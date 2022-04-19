package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Recurso;

public interface RecursoDAO {
    List<Recurso> getRecursosDisponibles() throws PersistenceException;

    void insertarRecurso(Recurso r) throws PersistenceException;
}
