package edu.eci.cvds.view;

import java.sql.Date;
import java.sql.Timestamp;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.google.inject.Inject;

import org.slf4j.LoggerFactory;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

@ManagedBean(name = "reservarBean")
@SessionScoped
public class ReservarBean extends BasePageBean {

    @Inject
    private ProyectoServices userServices;

    public int recurrencia=0;
    public Date fechaFinal;
    public String mostrar="None";

    public void reservar(int idUser){
        System.out.println(recurrencia); 
        try{
            Reserva reserva = new Reserva(idUser, 1, Timestamp.valueOf("2022-05-02 09:00:00"), Timestamp.valueOf("2022-05-02 10:00:00"),recurrencia);
            userServices.insertarReserva(reserva);
        }catch(ServicesException e){

        }
    }

    public void cambioEstado(){
        if(recurrencia!=0){
            mostrar="True";
        } else {
            mostrar="None";
        }
    }

    public String getMostrar() {
        return mostrar;
    }

    public void setMostrar(String mostrar) {
        this.mostrar = mostrar;
    }

    public int getRecurrencia() {
        return recurrencia;
    }
    public void setRecurrencia(int recurrencia) {
        this.recurrencia = recurrencia;
    }
    public Date getFechaFinal() {
        return fechaFinal;
    }
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    
}
