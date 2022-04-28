package edu.eci.cvds.view;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ManagedBean(name = "misReservasBean")
@SessionScoped
public class MisReservasBean extends BasePageBean {
    @Inject
    private ProyectoServices userServices;

    // Filtro para la busqueda donde en 0 no hay, 1 son las pasadas y 2 las
    // canceladas
    private Integer filter = 0;

    public String title = "Mis reservas";

    private static ArrayList<Reserva> reservas = new ArrayList<>();

    public List<Reserva> consultarReservas() {
        reservas = new ArrayList<>();
        java.sql.Timestamp timestamp1 = java.sql.Timestamp.valueOf("2022-04-23 10:10:10.0");
        java.sql.Timestamp timestamp2 = java.sql.Timestamp.valueOf("2022-04-23 11:10:10.0");
        if (this.filter == 0) {
            reservas = new ArrayList<>();
            Reserva r1 = new Reserva(1, 1, 1, timestamp1, timestamp2);
            Reserva r2 = new Reserva(2, 1, 14, timestamp1, timestamp2);
            Reserva r3 = new Reserva(3, 2, 14, timestamp1, timestamp2);
            Reserva r4 = new Reserva(4, 2, 1, timestamp1, timestamp2);
            for (int i = 0; i < 6; i++) {
                reservas.add(r1);
                reservas.add(r2);
                reservas.add(r3);
                reservas.add(r4);
            }
        } else if (this.filter == 1) {
            Reserva r1 = new Reserva(1, 1, 1, timestamp1, timestamp2);
            Reserva r2 = new Reserva(2, 1, 14, timestamp1, timestamp2);
            reservas.add(r1);
            reservas.add(r2);
        } else if (this.filter == 2) {
            Reserva r3 = new Reserva(3, 2, 14, timestamp1, timestamp2);
            Reserva r4 = new Reserva(4, 2, 1, timestamp1, timestamp2);
            reservas.add(r3);
            reservas.add(r4);
        }
        return reservas;
    }

    public void setfilterOld() {
        this.title = "Mis reservas pasadas";
        this.filter = 1;
        this.callFilters();
        this.consultarReservas();
    }

    public void setfilterCancelled() {
        this.title = "Mis reservas canceladas";
        this.filter = 2;
        this.callFilters();
        this.consultarReservas();
    }

    public void removeFilters() {
        this.title = "Mis reservas";
        this.filter = 0;
        this.callFilters();
        this.consultarReservas();
    }

    public void callFilters() {
        this.showFilterOld();
        this.showFilterCancelled();
        this.showAll();
        this.showTableButtons();
    }

    public String showFilterOld() {
        return (this.filter != 1) ? "" : "none";
    }

    public String showFilterCancelled() {
        return (this.filter != 2) ? "" : "none";
    }

    public String showTableButtons() {
        return (this.filter == 0) ? "" : "none";
    }

    public String showAll() {
        return (this.filter != 0) ? "" : "none";
    }

    public String getTitle() {
        return this.title;
    }

    public void deleteBooking(int idReserva) {
        // Cambiar por desarrollo de la Historia #8
        System.out.println(idReserva);
        messageError("la funcionalidad de cancelar está en construcción.");
    }

    public void detailBooking(int idReserva) {
        System.out.println(idReserva);
        messageError("la funcionalidad de detalles está en construcción.");
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
}
