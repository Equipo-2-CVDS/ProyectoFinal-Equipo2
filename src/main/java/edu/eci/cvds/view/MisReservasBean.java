package edu.eci.cvds.view;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import com.mysql.fabric.xmlrpc.base.Value;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "misReservasBean")
@SessionScoped
@ViewScoped
public class MisReservasBean extends BasePageBean {
    @Inject
    private ProyectoServices userServices;

    // Filtro para la busqueda donde en 0 no hay, 1 son las pasadas y 2 las
    // canceladas
    private Integer filter = 0;

    public String title = "Reservas";

    public final static char filterNewest = 'n';

    public final static char filterOlders = 'o';

    private static List<Reserva> reservas = new ArrayList<>();

    private int idUsuario = 0;

    public List<Reserva> consultarReservas(int id) {
        Timestamp today = new Timestamp(System.currentTimeMillis());
        try {
            List<Reserva> refreshReserva = userServices.getReservasUsuario(id);
            if (idUsuario != 0) {
                reservas = userServices.getReservasRecurso(idUsuario);
            } else if (userServices.getRol(id).equals("Administrador")) {
                reservas = userServices.getReservas();
            } else if (this.filter == 0) {
                reservas = filterByDate(refreshReserva, today, filterNewest);
            } else if (this.filter == 1) {
                reservas = filterByDate(refreshReserva, today, filterOlders);
            } else if (this.filter == 2) {
                reservas = new ArrayList<>();
            }
        } catch (ServicesException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public void setfilterOld(int id) {
        this.title = "Reservas pasadas";
        this.filter = 1;
        this.callFilters(id);
        this.consultarReservas(id);
    }

    public void setfilterCancelled(int id) {
        this.title = "Reservas canceladas (¡Funcionalidad en construcción!)";
        this.filter = 2;
        this.callFilters(id);
        this.consultarReservas(id);
    }

    public void removeFilters(int id) {
        this.title = "Reservas";
        this.filter = 0;
        this.callFilters(id);
        this.consultarReservas(id);
    }

    public void callFilters(int id) {
        try {
            this.showFilterCancelled(id);
            this.showTableButtonDelete(id);
            this.showFilterOld(id);
        } catch (ServicesException e) {
            e.printStackTrace();
        }
        this.showAll();
        this.showTableButtonDetail();
    }

    public String showFilterOld(int id) throws ServicesException {
        return (this.filter != 1 && userServices.getRol(id).equals("Comunidad")) ? "" : "none";
    }

    public String showFilterCancelled(int id) throws ServicesException {
        return (this.filter != 2 && userServices.getRol(id).equals("Comunidad")) ? "" : "none";
    }

    public String showTableButtonDetail() {
        return (this.filter == 0) ? "" : "none";
    }

    public String showTableButtonDelete(int id) throws ServicesException {
        return (this.filter == 0 && userServices.getRol(id).equals("Comunidad")) ? "" : "none"; // here
    }

    public String showAll() {
        return (this.filter != 0) ? "" : "none";
    }

    public String getTitle() {
        return this.title;
    }

    public String isAdmin(int id) throws ServicesException {
        System.out.println(userServices.getRol(id).equals("Administrador"));
        return userServices.getRol(id).equals("Administrador") ? "" : "none";
    }

    public void deleteBooking(int idUsuario) {
        // Cambiar por desarrollo de la Historia #8
        System.out.println(idUsuario);
        messageError("la funcionalidad de cancelar está en construcción.");
    }

    public void detailBooking(int idUsuario) {
        System.out.println(idUsuario);
        messageError("la funcionalidad de detalles está en construcción.");
    }

    public List<Reserva> filterByDate(List<Reserva> initialArray, Timestamp date, char filterType) {
        List<Reserva> filteredList = new ArrayList<>();

        for (Reserva reserva : initialArray) {
            int comparation = reserva.getHasta().compareTo(date);
            if (comparation > 0 && filterType == filterNewest) {
                filteredList.add(reserva);
            }
            if (comparation < 0 && filterType == filterOlders) {
                filteredList.add(reserva);
            }
        }
        return filteredList;
    }

    /**
     * Lanza el mensaje de error
     * 
     * @param message
     */
    private void messageError(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Lo sentimos!, ", message));
    }

    public int getidUsuario() {
        return this.idUsuario;
    }

    public void setidUsuario(int event) {
        idUsuario = event;
    }

    public void serchReservasById() throws ServicesException {
        reservas = userServices.getReservasRecurso(idUsuario);
    }

    public void reset() throws ServicesException {
        idUsuario = 0;
    }
}
