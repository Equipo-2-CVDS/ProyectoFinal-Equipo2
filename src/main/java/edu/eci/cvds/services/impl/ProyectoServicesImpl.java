package edu.eci.cvds.services.impl;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.RolesDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

public class ProyectoServicesImpl implements ProyectoServices{

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private RolesDAO rolesDAO;

    @Override
    public Usuario buscarUsuario(String nombre) throws ServicesException {
        try {
            System.out.println(usuarioDAO);
			return usuarioDAO.buscarUsuario(nombre);
		} catch (PersistenceException ex) {
			throw new ServicesException("Error buscando usuario:" + nombre, ex);
		}
    }

    @Override
    public String getRol(int id) throws ServicesException {
        try{
            return rolesDAO.getRol(id);
        } catch(PersistenceException ex){
            throw new ServicesException("Error buscando rol:" + id, ex);
        }
        
    }
    
}
