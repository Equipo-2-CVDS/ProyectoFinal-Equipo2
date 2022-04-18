package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;

import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.RecursoDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.RecursoMapper;

public class MyBatisRecursoDAO implements RecursoDAO {
    @Inject
    RecursoMapper recursoMapper;


    @Override
    public String getRecurso() throws PersistenceException {
        try {
            return recursoMapper.consultarRecurso().getNombre();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }
    }
}