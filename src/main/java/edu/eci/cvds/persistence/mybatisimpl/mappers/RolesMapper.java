package edu.eci.cvds.persistence.mybatisimpl.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Rol;

public interface RolesMapper {   
    Rol consultarRol(@Param("id")int id);
}
