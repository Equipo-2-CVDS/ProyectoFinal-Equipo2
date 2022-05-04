package edu.eci.cvds.view;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

@ManagedBean(name = "reservarBean")
@SessionScoped
public class ReservarBean extends BasePageBean {

    @Inject
    private ProyectoServices userServices;

    private int recurrencia = 0;
    private Date fechaFinal;
    private String mostrar = "None";
    private String encabezado = "Hubo errores en las siguientes reservas: ";
    private String fallos = "";

    public void reservar(int idUsuario) {
        try {
            Timestamp fechaInicial = Timestamp.valueOf("2022-05-02 11:00:00");
            int horas = 1;
            int idRecurso = 1;
            LocalDateTime fechaInsercion = fechaInicial.toLocalDateTime();
            LocalDateTime fechaFinalInsercion = fechaFinal==null ? fechaInicial.toLocalDateTime():new Timestamp(fechaFinal.getTime()).toLocalDateTime().plusDays(1);
            while (fechaInsercion.isBefore(fechaFinalInsercion) || fechaInsercion.isEqual(fechaFinalInsercion)) {
                if (userServices.getHorarioDia(idRecurso, fechaInsercion.getDayOfWeek().getValue()) != null) {
                    if(busquedaReservas(idRecurso, fechaInsercion, horas)){
                        Reserva reserva = new Reserva(idUsuario, idRecurso, Timestamp.valueOf(fechaInsercion),
                                Timestamp.valueOf(fechaInsercion.plusHours(horas)), recurrencia);
                        userServices.insertarReserva(reserva);
                    } else{
                        fallos = fallos + " " + fechaInsercion.toString();
                    }
                } else {                  
                    fallos = fallos + " " + fechaInsercion.toString();
                }
                fechaInsercion = sumarRecurrencia(fechaInsercion);
            }
            if (fallos.isEmpty()) {
                encabezado = "Se ha reservado correctamente";
            }
            clear();
        } catch (Exception e) {
            encabezado = "No se pudo reservar";
            fallos = "Intentelo de nuevo";
        }
    }

    private void clear(){
        this.recurrencia = 0;
        this.fechaFinal=new Date();
        this.mostrar = "None";
    }

    public void redirect(int opt) throws IOException{
        clearText();
        if (opt==0){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/misReservas.xhtml");
        } else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/home.xhtml");
        }
    }
    public void clearText(){
        this.fallos="";
        this.encabezado="Hubo errores en las siguientes reservas: ";
    }

    private boolean busquedaReservas(int idRecurso, LocalDateTime fechaInsercion, int horas)
            throws ServicesException {
        List<Reserva> reservas = userServices.getReservasRecurso(idRecurso);
        for (Reserva re : reservas) {
            LocalDateTime desde = re.getDesde().toLocalDateTime();
            LocalDateTime hasta = re.getHasta().toLocalDateTime();
            if ((desde.isBefore(fechaInsercion) && hasta.isAfter(fechaInsercion))
                    || (desde.isBefore(fechaInsercion.plusHours(horas))
                            && hasta.isAfter(fechaInsercion.plusHours(horas)))||(desde.isEqual(fechaInsercion))) {
                return false;
            }
        }
        return true;
    }

    private LocalDateTime sumarRecurrencia(LocalDateTime fechaInsercion) {
        switch (recurrencia) {
            case 0:
                fechaInsercion = fechaInsercion.plusMonths(1);
                break;
            case 1:
                fechaInsercion = fechaInsercion.plusDays(1);
                break;
            case 2:
                fechaInsercion = fechaInsercion.plusWeeks(1);
                break;
            case 3:
                fechaInsercion = fechaInsercion.plusMonths(1);
                break;
            default:
                break;
        }
        return fechaInsercion;
    }

    public void cambioEstado() {
        if (recurrencia != 0) {
            mostrar = "True";
        } else {
            mostrar = "None";
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

    public String getFallos() {
        return fallos;
    }

    public void setFallos(String fallos) {
        this.fallos = fallos;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }  

}
