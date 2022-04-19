package edu.eci.cvds.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.slf4j.LoggerFactory;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "recursoBean")
@SessionScoped
public class RecursoBean extends BasePageBean {

    @Inject
    private ProyectoServices userServices;

    private String[] dias;
    private int id;
    private String nombre;
    private String tipo;
    private int capacidad;
    private String ubicacion;
    private boolean estado=true;
    private String desde;
    private String hasta;

    public void insertarRecurso() throws ServicesException{
        try {
            int inicio = Integer.parseInt(desde);
            int fin = Integer.parseInt(hasta);
            if(inicio < fin){
                userServices.insertarRecurso(new Recurso(nombre,tipo,capacidad,ubicacion,estado));
                int idRecurso = userServices.getRecurso(nombre).getId();
                for(String i : dias){
                    userServices.insertarHorario(new Horario(idRecurso, Integer.parseInt(i), new Time(inicio, 0, 0), new Time(fin, 0, 0)));
                }
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Agrego de forma exitosa la Oferta","");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else{
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La hora hasta debe ser mayor a la hora desde","");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }     
        }catch(ServicesException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "" + e.getMessage(),"");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        clear();
    }

    public void clear(){
        dias=new String[5];
        nombre="";
        tipo="";
        capacidad=0;
        ubicacion="";
        desde="";
        hasta="";
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String[] getDias() {
        return dias;
    }

    public void setDias(String[] dias) {
        this.dias = dias;
    }

}
