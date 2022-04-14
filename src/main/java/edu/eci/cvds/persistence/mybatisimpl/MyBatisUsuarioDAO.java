package edu.eci.cvds.persistence.mybatisimpl;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.UsuarioMapper;

public class MyBatisUsuarioDAO implements UsuarioDAO {

	@Inject
	UsuarioMapper usuarioMapper;

	@Override
	public Usuario buscarUsuario(String nombre) throws PersistenceException {
		try {
			return usuarioMapper.getUsuario(nombre);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}

}
