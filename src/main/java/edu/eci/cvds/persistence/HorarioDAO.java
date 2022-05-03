package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Horario;


public interface HorarioDAO {

    public List<Horario> getHorariosDisponibles(int idRecurso) throws PersistenceException;

    public Horario getHorarioDia(int idRecurso, int idDia) throws PersistenceException;

    public void insertarHorario(Horario h) throws PersistenceException;
    
}