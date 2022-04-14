package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;

import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.RecursoDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.RecursoMapper;

public class MyBatisRecursoDAO implements RecursoDAO {
    @Inject
    RecursoMapper RecursosMapper;


    @Override
    public String getRecurso(int id) throws PersistenceException {
        try {
            return RecursosMapper.getRecurso(id).getNombre();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }
    }
}