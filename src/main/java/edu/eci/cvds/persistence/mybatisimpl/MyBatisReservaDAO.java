package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Table;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.ReservaDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.ReservaMapper;

public class MyBatisReservaDAO implements ReservaDAO {
    @Inject
    ReservaMapper reservaMapper;

    @Override
    public void insertarReserva(Reserva re) throws PersistenceException {
        try {
            reservaMapper.insertarReserva(re);
        } catch (Exception e) {
            throw new PersistenceException("Error al insertar reserva", e);
        }

    }

    @Override
    public List<Reserva> getReservasUsuario(int idUsuario) throws PersistenceException {
        try {
            return reservaMapper.getReservasUsuario(idUsuario);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }
    }

    @Override
    public List<Reserva> getReservas() throws PersistenceException {
        try {
            return reservaMapper.getReservas();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }
    }

    public List<Reserva> getReservasRecurso(int idRecurso) throws PersistenceException {
        try {
            return reservaMapper.getReservasRecurso(idRecurso);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }
    }

    @Override
    public Reserva getReservaPorId(int id) throws PersistenceException {
        try {
            return reservaMapper.getReservaPorId(id);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }
    }

    @Override
    public void cancelarReserva(int id) throws PersistenceException {
        try {
            reservaMapper.cancelarReserva(id);
        } catch (Exception e) {
            throw new PersistenceException("Error al cancelar", e);
        }
    }

    @Override
    public List<Table> getTable() throws PersistenceException {
        try {
            return reservaMapper.getTable();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }
    }
}
