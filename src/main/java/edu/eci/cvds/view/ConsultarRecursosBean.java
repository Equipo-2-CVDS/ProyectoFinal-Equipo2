package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@ManagedBean(name = "consultarRecursosBean")
@SessionScoped
public class ConsultarRecursosBean extends BasePageBean{
    @Inject
    private ProyectoServices userServices;
    private int id;
    private String nombre;
    private String tipo;
    private int capacidad;
    private String ubicacion;
    private boolean estado=true;

    public List<Recurso> consultarRecursos() throws ServicesException {
        if (!Objects.equals(tipo, "Seleccione tipo")){
            return userServices.getRecursosFromTipo(tipo);
        }
        else if (capacidad != 0){
            return userServices.getRecursosFromCapacidad(capacidad);
        }
        else if (!Objects.equals(ubicacion, "Seleccione ubicacion")){
            return userServices.getRecursosFromUbicacion(ubicacion);
        }
        return userServices.getRecursosDisponibles();
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
}
