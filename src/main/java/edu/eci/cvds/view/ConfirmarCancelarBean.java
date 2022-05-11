package edu.eci.cvds.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Inject;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.ProyectoServices;

@ManagedBean(name = "cancelarBean")
@SessionScoped
public class ConfirmarCancelarBean extends BasePageBean {
    @Inject
    private ProyectoServices userServices;

    public Reserva reserva;

    public String tipo;

    public Reserva getReserva() {
        return this.reserva;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String setTipo(String tipo) {
        return this.tipo = tipo;
    }

    public void setReserva(Reserva reserva) throws IOException {
        this.reserva = reserva;
    }

    public void inicializar(Reserva reserva) throws IOException {
        this.reserva = reserva;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/confirmacionCancelar.xhtml");
    }

    public String isRecurrent() {
        return (reserva.getRecurrencia() > 0) ? "" : "none";
    }

    public void delete(String value) throws PersistenceException, IOException {
        if (reserva.getRecurrencia() == 0) {
            try {
                userServices.cancelarReserva(reserva.getId());
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/misReservas.xhtml");
            } catch (Exception e) {
                messageError("No se pudo cancelar la reserva, lo sentimos");
            }
            return;
        }
        if (value.equals("")) {
            messageError("Debe seleccionar un tipo de cancelación recurrente");
            return;
        }
        List<Reserva> reservas = this.userServices.getReservasUsuario(reserva.getIdUsuario());
        List<Integer> idReservas = new ArrayList<Integer>();
        for (Reserva re : reservas) {
            // Las reservas que pertenezcan a la indicada coinciden en dia, hora y minuto ya
            // que se hacen de manera automatica y el metodo las trae ordenadas de la mas
            // antigua a la mas nueva
            if (re.getFechaSolicitado().getHours() == reserva.getFechaSolicitado().getHours()
                    && re.getFechaSolicitado().getMinutes() == reserva.getFechaSolicitado().getMinutes()
                    && re.getFechaSolicitado().getDay() == reserva.getFechaSolicitado().getDay()
                    && re.getRecurrencia() == reserva.getRecurrencia()
                    && !re.getEstado()) {
                idReservas.add(Integer.valueOf(re.getId()));
            }
        }
        int index = idReservas.indexOf(Integer.valueOf(reserva.getId()));
        this.casoRecurrencia(value, index, idReservas);
    }

    private void casoRecurrencia(String value, int index, List<Integer> idReservas) {
        switch (value) {
            case "1":
                for (Integer integer : idReservas) {
                    try {
                        userServices.cancelarReserva(integer);
                    } catch (Exception e) {
                        messageError("Ocurrió un error cancelando las reservas, lo sentimos");
                    }
                }
                break;
            case "2":
                try {
                    userServices.cancelarReserva(reserva.getId());
                } catch (Exception e) {
                    messageError("Ocurrió un error cancelando las reservas, lo sentimos");
                }
                break;
            case "3":
                for (int i = index; i <= idReservas.size() - 1; i++) {
                    try {
                        userServices.cancelarReserva(idReservas.get(i));
                    } catch (Exception e) {
                        messageError("Ocurrió un error cancelando las reservas, lo sentimos");
                    }
                }
                break;
            default:
                break;
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/misReservas.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean reservasRecurrentes(Reserva re) {
        if (re.getFechaSolicitado().getHours() == reserva.getFechaSolicitado().getHours()
                && re.getFechaSolicitado().getMinutes() == reserva.getFechaSolicitado().getMinutes()
                && re.getFechaSolicitado().getDay() == reserva.getFechaSolicitado().getDay()
                && re.getRecurrencia() == reserva.getRecurrencia()
                && !re.getEstado()) {
            return true;
        } else
            return false;
    }

    private void messageError(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", message));
    }
}
