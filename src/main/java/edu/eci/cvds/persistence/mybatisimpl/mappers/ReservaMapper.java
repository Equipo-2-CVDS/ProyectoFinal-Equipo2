package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Reserva;

public interface ReservaMapper {

    public void insertarReserva(@Param("re") Reserva re);

    public List<Reserva> getReservasUsuario(@Param("idUsuario") int idUsuario);

    public List<Reserva> getReservas();

    public List<Reserva> getReservasRecurso(@Param("idRecurso") int idRecurso);

    public Reserva getReservaPorId(@Param("id") int id);

    public void cancelarReserva(@Param("id") int id);

    List<String> getRecursoMasUsado();

    List<String> getRecursoMenosUsado();

    List<String> getHorariosMasUsados();

    List<String> getHorariosMenosUsados();

    List<Reserva> getReservasRecurentes();

    List<Reserva> getReservasCanceladas();

}
