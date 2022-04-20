package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Horario;


public interface HorarioMapper {

    public List<Horario> getHorariosDisponibles(@Param("idRecurso") int idRecurso);

    public void insertarHorario(@Param("h") Horario h);
    
}
