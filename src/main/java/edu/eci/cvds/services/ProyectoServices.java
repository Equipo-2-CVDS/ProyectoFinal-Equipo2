package edu.eci.cvds.services;

import java.util.List;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
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

    /**
     * Consulta los recursos de la biblioteca
     *
     * @return Recurso
     * @throws ServicesException
     */
    public List<Recurso> getRecursosDisponibles() throws ServicesException;

    /**
     * Consulta los recursos con el filtro tipo
     *
     * @return List<Recurso>
     */
    public List<Recurso> getRecursosFromTipo(String tipo) throws ServicesException;

    /**
     * Consulta los recursos con el filtro capacidad
     *
     * @return List<Recurso>
     */
    public List<Recurso> getRecursosFromCapacidad(int capacidad) throws ServicesException;

    /**
     * Consulta los recursos con el filtro ubicacion
     *
     * @return List<Recurso>
     */
    public List<Recurso> getRecursosFromUbicacion(String ubicacion) throws ServicesException;

    /**
     * Inserta recurso en la base de datos
     * @param r Recurso a insertar
     * @throws ServicesException
     */
    public void insertarRecurso(Recurso r) throws ServicesException; 

    /**
     * Consulta los horarios de un recurso
     * @param idRecurso
     * @return
     * @throws ServicesException
     */
    public List<Horario> getHorariosDisponibles(int idRecurso) throws ServicesException;

    /**
     * Inserta horario en la base de datos
     * @param h horario a insertar
     * @throws ServicesException
     */
    public void insertarHorario(Horario h) throws ServicesException; 

    /**
     * Consulta el recurso segun el nombre
     * 
     * @param nombre nombre del recurso
     * @return Recurso
     * @throws ServicesException
     */
    public Recurso getRecurso(String nombre) throws ServicesException;
}
