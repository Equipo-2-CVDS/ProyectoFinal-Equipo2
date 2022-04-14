package edu.eci.cvds.persistence.mybatisimpl.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Recurso;

public interface RecursoMapper {
    public Recurso consultarRecurso(@Param("id")int id);
}
