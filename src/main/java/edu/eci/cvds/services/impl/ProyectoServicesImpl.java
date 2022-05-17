package edu.eci.cvds.services.impl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Table;
import edu.eci.cvds.entities.UsuRecuRese;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.RecursoDAO;
import edu.eci.cvds.persistence.ReservaDAO;
import edu.eci.cvds.persistence.RolesDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.HorarioDAO;
import edu.eci.cvds.services.ProyectoServices;

public class ProyectoServicesImpl implements ProyectoServices {

        @Inject
        private UsuarioDAO usuarioDAO;

        @Inject
        private RolesDAO rolesDAO;

        @Inject
        private RecursoDAO recursoDAO;

        @Inject
        private HorarioDAO horarioDAO;

        @Inject
        private ReservaDAO reservaDAO;

        @Override
        public Usuario buscarUsuario(String nombre) throws PersistenceException {
                return usuarioDAO.buscarUsuario(nombre);
        }

        @Override
        public String getRol(int id) throws PersistenceException {
                return rolesDAO.getRol(id);
        }

        @Override
        public List<Recurso> getRecursosDisponibles() throws PersistenceException {
                return recursoDAO.getRecursosDisponibles();
        }

        @Override
        public void insertarRecurso(Recurso r) throws PersistenceException {
                recursoDAO.insertarRecurso(r);
        }

        @Override
        public List<Horario> getHorariosDisponibles(int idRecurso) throws PersistenceException {
                return horarioDAO.getHorariosDisponibles(idRecurso);
        }

        @Override
        public void insertarHorario(Horario h) throws PersistenceException {
                horarioDAO.insertarHorario(h);
        }

        @Override
        public Recurso getRecurso(String nombre) throws PersistenceException {
                return recursoDAO.getRecurso(nombre);
        }

        @Override
        public void insertarReserva(Reserva re) throws PersistenceException {
                reservaDAO.insertarReserva(re);
        }

        @Override
        public List<Reserva> getReservasUsuario(int idUsuario) throws PersistenceException {
                return reservaDAO.getReservasUsuario(idUsuario);
        }

        @Override
        public List<Reserva> getReservas() throws PersistenceException {
                return reservaDAO.getReservas();
        }

        @Override
        public List<Recurso> getRecursos() throws PersistenceException {
                return recursoDAO.getRecursos();
        }

        public Horario getHorarioDia(int idRecurso, int idDia) throws PersistenceException {
                return horarioDAO.getHorarioDia(idRecurso, idDia);
        }

        @Override
        public List<Reserva> getReservasRecurso(int idRecurso) throws PersistenceException {
                return reservaDAO.getReservasRecurso(idRecurso);
        }

        @Override
        public UsuRecuRese getUsuRecuRese(int id) throws PersistenceException {
                Reserva reservas = reservaDAO.getReservaPorId(id);
                Usuario u = buscarUsuarioPorId(reservas.getIdUsuario());
                Recurso re = getRecursoPorId(reservas.getIdRecurso());
                String infoRecurso = "Capacidad: " + Integer.toString(re.getCapacidad()) + ", Ubicacion: "
                                + re.getUbicacion() + ", Tipo: " + re.getTipo();
                UsuRecuRese res = (new UsuRecuRese(re.getNombre(), infoRecurso, reservas.getFechaSolicitado(),
                                reservas.getDesde(), reservas.getHasta(), u.getNombre(), u.getPrograma(),
                                reservas.getRecurrencia()));
                return res;
        }

        @Override
        public Usuario buscarUsuarioPorId(int id) throws PersistenceException {
                return usuarioDAO.getUsuarioPorId(id);
        }

        @Override
        public Recurso getRecursoPorId(int id) throws PersistenceException {
                return recursoDAO.getRecursoPorId(id);
        }

        @Override
        public Reserva getReservaPorId(int id) throws PersistenceException {
                return reservaDAO.getReservaPorId(id);
        }

        @Override
        public void cancelarReserva(int id) throws PersistenceException {
                reservaDAO.cancelarReserva(id);
        }

        @Override
        public List<String> getRecursoMasUsado() throws PersistenceException {
                return reservaDAO.getRecursoMasUsado();
        }

        @Override
        public List<String> getRecursoMenosUsado() throws PersistenceException {
                return reservaDAO.getRecursoMenosUsado();
        }

        @Override
        public List<String> getHorariosMasUsados() throws PersistenceException {
                return reservaDAO.getHorariosMasUsados();
        }

        @Override
        public List<String> getHorariosMenosUsados() throws PersistenceException {
                return reservaDAO.getHorariosMenosUsados();
        }

        @Override
        public List<Reserva> getReservasRecurentes() throws PersistenceException {
                return reservaDAO.getReservasRecurentes();
        }

        @Override
        public List<Reserva> getReservasCanceladas() throws PersistenceException {
                return reservaDAO.getReservasCanceladas();
        }

        @Override
        public List<Table> getTable() throws PersistenceException {
                return reservaDAO.getTable();
        }

}
