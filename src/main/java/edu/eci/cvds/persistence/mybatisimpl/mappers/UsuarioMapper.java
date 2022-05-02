package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.Usuario;
import org.apache.ibatis.annotations.Param;

public interface UsuarioMapper {

	public Usuario getUsuario(@Param("nombre") String nombre);
	public Usuario getUsuarioPorId(@Param("id") int id);

}
