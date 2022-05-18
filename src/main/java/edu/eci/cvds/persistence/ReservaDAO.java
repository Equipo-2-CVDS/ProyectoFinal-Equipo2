package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Table;

public interface ReservaDAO {

    List<Table> getTable() throws PersistenceException;

    void insertarReserva(Reserva re) throws PersistenceException;

    List<Reserva> getReservasUsuario(int idUsuario) throws PersistenceException;

    List<Reserva> getReservas() throws PersistenceException;

    Reserva getReservaPorId(int id) throws PersistenceException;

    List<Reserva> getReservasRecurso(int idRecurso) throws PersistenceException;

    void cancelarReserva(int id) throws PersistenceException;

}
