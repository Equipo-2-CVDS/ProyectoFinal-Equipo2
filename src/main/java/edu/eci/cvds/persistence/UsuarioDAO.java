package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Usuario;

public interface UsuarioDAO {

	public Usuario buscarUsuario(String nombre) throws PersistenceException;
	
}

