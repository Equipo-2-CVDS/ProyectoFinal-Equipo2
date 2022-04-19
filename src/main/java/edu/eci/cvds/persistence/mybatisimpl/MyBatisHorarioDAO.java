package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.HorarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.HorarioMapper;

public class MyBatisHorarioDAO implements HorarioDAO {
    @Inject
    HorarioMapper horarioMapper;


    @Override
    public List<Horario> getHorariosDisponibles(int idRecurso) throws PersistenceException {
        try {
            return horarioMapper.getHorariosDisponibles(idRecurso);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }
    }


    @Override
    public void insertarHorario(Horario h) throws PersistenceException {
        try {
            horarioMapper.insertarHorario(h);
        } catch (Exception e) {
            throw new PersistenceException("Error al insertar Horario", e);
        }
        
    }
}