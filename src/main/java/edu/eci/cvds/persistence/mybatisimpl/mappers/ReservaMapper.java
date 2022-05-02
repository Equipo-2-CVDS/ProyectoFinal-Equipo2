package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Reserva;

public interface ReservaMapper {
    
    public void insertarReserva(@Param("re") Reserva re);
    
    public List<Reserva> getReservasUsuario(@Param("idUsuario") int idUsuario);
    public List<Reserva> getReservas();
    public List<Reserva> getReservasRecurso(@Param("idRecurso") int idRecurso);
    
}
