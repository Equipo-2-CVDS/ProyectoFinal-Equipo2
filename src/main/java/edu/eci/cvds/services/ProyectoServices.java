package edu.eci.cvds.services;

import edu.eci.cvds.entities.Usuario;

public interface ProyectoServices {


    /**
     * Consulta el usuario dado su nombre
     * 
     * @return Usuario
     * @throws ServicesException
     */
    public Usuario buscarUsuario(String nombre) throws ServicesException;

    /**
     * Retorna el rol correspondiente al id
     * @param id
     * @return
     * @throws ServicesException
     */
    public String getRol(int id) throws ServicesException;
}
