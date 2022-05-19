package edu.eci.cvds.services;

import java.util.List;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Table;
import edu.eci.cvds.entities.UsuRecuRese;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;

public interface ProyectoServices {

    /**
     * Consulta el usuario dado su nombre
     * 
     * @return Usuario
     * @throws ServicesException
     */
    public Usuario buscarUsuario(String nombre) throws PersistenceException;

    /**
     * Consulta el usuario dado su id
     * 
     * @return Usuario
     * @throws ServicesException
     */
    public Usuario buscarUsuarioPorId(int id) throws PersistenceException;

    /**
     * Retorna el rol correspondiente al id
     * 
     * @param id
     * @return
     * @throws ServicesException
     */
    public String getRol(int id) throws PersistenceException;

    /**
     * Consulta los recursos de la biblioteca
     *
     * @return Recurso
     * @throws ServicesException
     */
    public List<Recurso> getRecursosDisponibles() throws PersistenceException;

    /**
     * Inserta recurso en la base de datos
     * 
     * @param r Recurso a insertar
     * @throws ServicesException
     */
    public void insertarRecurso(Recurso r) throws PersistenceException;

    /**
     * Consulta los horarios de un recurso
     * 
     * @param idRecurso
     * @return
     * @throws ServicesException
     */
    public List<Horario> getHorariosDisponibles(int idRecurso) throws PersistenceException;

    /**
     * Consulta si el recurso tiene horario un dia especifico
     * 
     * @param idRecurso id del recurso a consultar
     * @param idDia     id del dia a consultar;
     *                  Cada dia viene dado por su numero en la semana, tal que asi:
     *                  {1:lunes,2:martes,3:miercoles,4:jueves,5:viernes}
     * @return El horario de el recurso ese dia
     * @throws ServicesException
     */
    public Horario getHorarioDia(int idRecurso, int idDia) throws PersistenceException;

    /**
     * Inserta horario en la base de datos
     * 
     * @param h horario a insertar
     * @throws ServicesException
     */
    public void insertarHorario(Horario h) throws PersistenceException;

    /**
     * Consulta el recurso segun el nombre
     * 
     * @param nombre nombre del recurso
     * @return Recurso
     * @throws ServicesException
     */
    public Recurso getRecurso(String nombre) throws PersistenceException;

    /**
     * Consulta los recursos
     *
     * @return Recurso
     * @throws ServicesException
     */
    public List<Recurso> getRecursos() throws PersistenceException;

    /**
     * Consulta el recurso segun el id
     * 
     * @param id id del recurso
     * @return Recurso
     * @throws ServicesException
     */
    public Recurso getRecursoPorId(int id) throws PersistenceException;

    /**
     * Inserta reserva en la base de datos
     * 
     * @param re Reserva a insertar
     * @throws ServicesException
     */
    public void insertarReserva(Reserva re) throws PersistenceException;

    /**
     * Consulta las reservas de un usuario
     * 
     * @param idUsuario usuario a consultar las reservas
     * @return
     * @throws ServicesException
     */
    public List<Reserva> getReservasUsuario(int idUsuario) throws PersistenceException;

    /**
     * Consulta las reservas de un usuario
     * 
     * @param idUsuario usuario a consultar las reservas
     * @return
     * @throws ServicesException
     */
    public List<Reserva> getReservas() throws PersistenceException;

    /**
     * Consulta las reservas de un recurso
     * 
     * @param idRecurso
     * @return
     * @throws ServicesException
     */
    public List<Reserva> getReservasRecurso(int idRecurso) throws PersistenceException;

    /**
     * Consulta informacion detallada de reservas
     * 
     * @return
     * @throws ServicesException
     */
    public UsuRecuRese getUsuRecuRese(int id) throws PersistenceException;

    /**
     * Consulta el recurso segun el id
     * 
     * @param id id del recurso
     * @return Recurso
     * @throws ServicesException
     */
    public Reserva getReservaPorId(int id) throws PersistenceException;

    /**
     * cancela elrecurso
     * 
     * @param id
     * @param estado
     * @throws PersistenceException
     */
    public void cancelacionConfirmada(int id) throws PersistenceException;

    /**
     * Consulta el recurso segun el id
     *
     * @param id     id del recurso
     * @param estado estado del recurso
     * @throws ServicesException
     */
    void cambiarEstadoRecurso(int i, boolean estado) throws PersistenceException;

    /**
     * Cancela una reserva segun el id
     * 
     * @param id id de la reserva
     * @throws ServicesException
     */
    public void cancelarReserva(int id, boolean estado) throws PersistenceException;

    /**
     * Consulta la tabla de reportes
     * 
     * @return Lista de table
     * @throws PersistenceException
     */
    public List<Table> getTable() throws PersistenceException;
}
