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

@ManagedBean(name = "confirmacionCancelarRecursoBean")
@SessionScoped

public class ConfirmacionCancelarRecursoBean {
    @Inject
    private ProyectoServices userServices;

    private int idRecurso;

    public void cancelarReserva(){
        try {
            System.out.println(idRecurso);
            for (int i = 0; i < userServices.getReservasRecurso(idRecurso).size(); i++){
                System.out.println(userServices.getReservasRecurso(idRecurso).get(i).getId());
                userServices.cancelarReserva(userServices.getReservasRecurso(idRecurso).get(i).getId());
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    public int getIdRecurso(){
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }
}
