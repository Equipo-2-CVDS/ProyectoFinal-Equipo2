package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.RecursoDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.RecursoMapper;

public class MyBatisRecursoDAO implements RecursoDAO {
    @Inject
    RecursoMapper recursoMapper;


    @Override
    public List<Recurso> getRecursosDisponibles() throws PersistenceException {
        try {
            return recursoMapper.getRecursosDisponibles();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }
    }


    @Override
    public void insertarRecurso(Recurso r) throws PersistenceException {
        try {
            recursoMapper.insertarRecurso(r);
        } catch (Exception e) {
            throw new PersistenceException("Error al insertar recurso", e);
        }
        
    }

    @Override
    public Recurso getRecurso(String nombre) throws PersistenceException {
        try {
            return recursoMapper.getRecurso(nombre);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar recurso con nombre: "+nombre, e);
        }
    }

    
}