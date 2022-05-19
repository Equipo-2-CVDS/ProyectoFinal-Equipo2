package edu.eci.cvds.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Inject;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.ProyectoServices;

@ManagedBean(name = "confirmacionCancelarRecursoBean")
@SessionScoped

public class ConfirmacionCancelarRecursoBean extends BasePageBean {
    @Inject
    private ProyectoServices userServices;

    private int idRecurso;
    private boolean estado;

    public void inicializar(int idRecurso, boolean estado) {
        this.estado = estado;
        this.idRecurso = idRecurso;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/confirmacionCancelarRecurso.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelarReserva() {
        try {
            List<Reserva> reservas = userServices.getReservasRecurso(idRecurso);
            for (Reserva r : reservas) {
                LocalDate now = LocalDate.now();
                LocalDate fechaReserva = r.getDesde().toLocalDateTime().toLocalDate();
                if (now.equals(fechaReserva) || now.isBefore(fechaReserva)) {
                    userServices.cancelarReserva(r.getId(), !estado);
                }
                messageError("Se cambio el estado del recurso y las reservas satisfactoriamente");
            }
            userServices.cambiarEstadoRecurso(idRecurso, !estado);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/modificarRecursos.xhtml");
        } catch (PersistenceException e) {
            messageError("No se pudo cancelar, intentelo de nuevo mas tarde");
        } catch (IOException e) {
            messageError("No se pudo redireccionar, la cancelacion quedo efectuada");
        }
    }

    public void cambiarEstado() {
        try {
            userServices.cambiarEstadoRecurso(idRecurso, !estado);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/modificarRecursos.xhtml");
            messageError("Se cambio el estado del recurso satisfactoriamente");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getText0() {
        return estado ? "Desea cancelar las reservas?" : "Desea restaurar las reservas?";
    }

    public String getText1() {
        return estado ? "Cancelar reservas" : "Restaurar reservas";
    }

    private void messageError(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "", message));
    }

    public boolean reservas() {
        boolean res = true;
        try {
            res = userServices.getReservasRecurso(idRecurso).size() > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        // System.out.println(res);
        return res;
    }

    public boolean reservasDiv() {
        boolean res = !reservas();
        return res;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
